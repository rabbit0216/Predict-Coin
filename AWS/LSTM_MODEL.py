#============ LSTM ===============
import requests
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.preprocessing import MinMaxScaler
from sklearn.model_selection import train_test_split
import tensorflow as tf
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, LSTM, Conv1D, Lambda
from tensorflow.keras.losses import Huber
from tensorflow.keras.optimizers import Adam
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint
import os
import datetime
import numpy as np
import pyupbit
import numpy

df = pyupbit.get_ohlcv("KRW-BTC", count=5000, period=1, interval="minute60")
df.to_csv("test.csv")

plt.figure(figsize=(16, 9))
date=df.reset_index()['index']
df1=df.reset_index()['close']
plt.xlabel('time')
plt.ylabel('price')
plt.plot(df1)

scaler=MinMaxScaler(feature_range=(0,1))
df1=scaler.fit_transform(np.array(df1).reshape(-1,1))

##splitting dataset into train and test split
training_size=int(len(df1)*0.65)
test_size=len(df1)-training_size
train_data,test_data=df1[0:training_size,:],df1[training_size:len(df1),:1]
training_size,test_size


# convert an array of values into a dataset matrix
def create_dataset(dataset, time_step=1):
	dataX, dataY = [], []
	for i in range(len(dataset)-time_step-1):
		a = dataset[i:(i+time_step), 0]   ###i=0, 0,1,2,3-----99   100 
		dataX.append(a)
		dataY.append(dataset[i + time_step, 0])
	return numpy.array(dataX), numpy.array(dataY)
 
 # reshape into X=t,t+1,t+2,t+3 and Y=t+4
time_step = 100
X_train, y_train = create_dataset(train_data, time_step)
X_test, ytest = create_dataset(test_data, time_step)

X_train =X_train.reshape(X_train.shape[0],X_train.shape[1] , 1)
X_test = X_test.reshape(X_test.shape[0],X_test.shape[1] , 1)

model=Sequential()
model.add(LSTM(50,return_sequences=True,input_shape=(100,1)))
model.add(LSTM(50,return_sequences=True))
model.add(LSTM(50))
model.add(Dense(1))

# Sequence 학습에 비교적 좋은 퍼포먼스를 내는 Huber()를 사용
loss = Huber()
optimizer = Adam(0.0005)
model.compile(loss=Huber(), optimizer=optimizer, metrics=['mse'])

model.summary()

model.summary()

# earlystopping - 35번 epoch통안 val_loss 개선이 없다면 학습을 멈춤
earlystopping = EarlyStopping(monitor='val_loss', patience=10)
# val_loss 기준 체크포인터도 생성
filename = os.path.join('tmp', 'ckeckpointer.ckpt')
checkpoint = ModelCheckpoint(filename, 
                             save_weights_only=True, 
                             save_best_only=True, 
                             monitor='val_loss', 
                             verbose=1)

history = model.fit(X_train,y_train,validation_data=(X_test,ytest),epochs=3,callbacks=[checkpoint, earlystopping])

### Predict
train_predict=model.predict(X_train)
test_predict=model.predict(X_test)
##Transformback to original form
train_predict=scaler.inverse_transform(train_predict)
test_predict=scaler.inverse_transform(test_predict)

### Plotting 
# shift train predictions for plotting
look_back=100
trainPredictPlot = numpy.empty_like(df1)
trainPredictPlot[:, :] = np.nan
trainPredictPlot[look_back:len(train_predict)+look_back, :] = train_predict
# shift test predictions for plotting
testPredictPlot = numpy.empty_like(df1)
testPredictPlot[:, :] = numpy.nan
testPredictPlot[len(train_predict)+(look_back*2)+1:len(df1)-1, :] = test_predict
# plot baseline and predictions
plt.figure(figsize=(16, 9))
plt.plot(scaler.inverse_transform(df1))
plt.plot(trainPredictPlot)
plt.plot(testPredictPlot)
plt.show()

testPredict=testPredictPlot[4279:,]
df2=df1[4280:,]

plt.figure(figsize=(16, 9))
plt.plot(scaler.inverse_transform(df2), label='actual')
plt.plot(testPredict, label='prediction')
plt.legend()
plt.show()

len(test_data)

x_input=test_data[1650:].reshape(1,-1)
x_input.shape

temp_input=list(x_input)
temp_input=temp_input[0].tolist()

# demonstrate prediction for next 10 days
from numpy import array

lst_output=[]
n_steps=100
i=0
while(i<72):
    
    if(len(temp_input)>100):
        #print(temp_input)
        x_input=np.array(temp_input[1:])
        print("{} day input {}".format(i,x_input))
        x_input=x_input.reshape(1,-1)
        x_input = x_input.reshape((1, n_steps, 1))
        #print(x_input)
        yhat = model.predict(x_input, verbose=0)
        print("{} day output {}".format(i,yhat))
        temp_input.extend(yhat[0].tolist())
        temp_input=temp_input[1:]
        #print(temp_input)
        lst_output.extend(yhat.tolist())
        i=i+1
    else:
        x_input = x_input.reshape((1, n_steps,1))
        yhat = model.predict(x_input, verbose=0)
        print(yhat[0])
        temp_input.extend(yhat[0].tolist())
        print(len(temp_input))
        lst_output.extend(yhat.tolist())
        i=i+1
    

print(lst_output)

day_new=np.arange(1,101)
day_pred=np.arange(101,173)

len(df1)

plt.plot(day_new,scaler.inverse_transform(df1[4900:]))
plt.plot(day_pred,scaler.inverse_transform(lst_output))

df3=df1.tolist()
df3.extend(lst_output)
plt.plot(df3[4990:])

df3=scaler.inverse_transform(df3).tolist()

plt.figure(figsize=(16, 9))
plt.plot(df3[5000:])

df4=scaler.inverse_transform(df2)
df3=df3[5000:]
date=date[4280:,]

#30일동안 과거 원본데이터(BTC) : df4
#30일동안의 과거 예측 데이터(BTC) : testPredict
#3일간 미래데이터(BTC) : df3
#과거 30일간의 날짜 : date

#======================== DB ========================

from sqlalchemy import create_engine
import pymysql
from dateutil.relativedelta import relativedelta
from datetime import datetime

#================미래 날짜 설정================, future_date를 데베에 넣어주면 됨
def setFutureDate(x):
  current_time = datetime.now()
  future_df = pd.DataFrame(columns=['date','close'])
  for price in x:
    current_time += relativedelta(hours=1)
    time = current_time.strftime("%Y-%m-%d %H:%M:%S")
    future_df = future_df.append(pd.DataFrame([[time, price[0]]], columns=['date','close']))
  return future_df

pd.options.display.float_format = '{:.5f}'.format 
future_df = setFutureDate(df3)
#future_df.set_index('date', inplace=True)

#==============과거 예측 데이터 + 날짜===============
def setPastDate(x):
  current_time = datetime.now()
  past_df = pd.DataFrame(columns=['date','close'])
  for price in x:
    current_time -= relativedelta(hours=1)
    time = current_time.strftime("%Y-%m-%d %H:%M:%S")
    past_df = past_df.append(pd.DataFrame([[time, price[0]]], columns=['date','close']))
  return past_df

past_df = setPastDate(testPredict)
past_df = past_df.iloc[:-1]

#================미래 예측 데이터 DB에 저장=================

future_db_connection_str = 'mysql+pymysql://LKK:12184878@13.124.58.0/future'
future_db_connection = create_engine(future_db_connection_str)
future_conn = future_db_connection.connect()


future_df.to_sql('BTC', con=future_db_connection, if_exists='replace')

#================과거 예측 데이터 DB에 저장=================
past_db_connection_str = 'mysql+pymysql://LKK:12184878@13.124.58.0/past'
past_db_connection = create_engine(past_db_connection_str)
past_conn = past_db_connection.connect()

past_df.to_sql('BTC', con=past_db_connection, if_exists='replace')

