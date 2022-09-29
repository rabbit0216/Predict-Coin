# Predict Coin

> LSTM 기반 암호화폐 가격 예측 어플리케이션

> 2021.08 - 2021.12
<br/>

## Description

* 투자 손실률을 줄일 수 있는 수단 증가시키기 위한 목적으로 해당 프로젝트를 진행하기로 결정
* `LSTM 모델`을 이용하여 암호화폐 가격을 예측하고 예측가와 현재가를 그래프와 표의 형태로 보여준다. 
</br>

### LSTM
<img src="Image/lstm_model.PNG" width="500">
</br>

### Architecture
<img src="Image/architecture.PNG" width="600"> 

* Front-end &nbsp;&nbsp;<img src="https://img.shields.io/badge/Android Studio-3DDC84?style=flat-square&logo=Android Studio&logoColor=white"/> 

  * 암호화폐 현재가와 예측가를 그래프와 표의 형태로 보여줌
<br/>

* Back-end
&nbsp;&nbsp;<img src="https://img.shields.io/badge/Python-3776AB?style=flat-square&logo=Python&logoColor=white"/> 
&nbsp;&nbsp;<img src="https://img.shields.io/badge/TensorFlow-FF6F00?style=flat-square&logo=TensorFlow&logoColor=white"/> 
&nbsp;&nbsp;<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/> 
&nbsp;&nbsp;<img src="https://img.shields.io/badge/PHP-777BB4?style=flat-square&logo=PHP&logoColor=white"/> 
&nbsp;&nbsp;<img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat-square&logo=Amazon EC2&logoColor=white"/> 
&nbsp;&nbsp;<img src="https://img.shields.io/badge/Apache-D22128?style=flat-square&logo=Apache&logoColor=white"/> 

  * LSTM 모델을 통해 예측한 가격을 DB에 저장
  * DB에 저장된 현재가와 예측가를 php로 보여줌
<br/>

## Image

<img src="Image/result.png" width="600">
