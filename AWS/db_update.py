#30일동안 과거 원본데이터(BTC) : df4
#30일동안의 과거 예측 데이터(BTC) : testPredict
#3일간 미래데이터(BTC) : df3
#과거 30일간의 날짜 : date
from LSTM_MODEL import *;

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

