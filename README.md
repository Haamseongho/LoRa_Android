# Ubinet 안드로이드 구성

>### 목차

<hr /> 


#  1. 서비스 개요


▷  부양자녀들이 사용할 모바일 어플리케이션으로 주요 기능은 
   디바이스 착용자의 현 위치와 전체 이동 경로 Tracking이 가능하다.
   
▶ 개인 정보를 부양자녀에게 노출시킬 것을 동의한다면 진행할 부분입니다.



▷ QR코드 인식을 통하여 환자 정보와 맞는 처방전을 제공해준다.

▶ 사용자 입장에서는 처방전을 빠르게 따로 받아볼 수 있고 번거로움을 덜어낼 수 있습니다. 
   또한 의사와 약사 입장에서는 쉽게 처방전을 주고 받음으로써 환자 및 투약을 필요로 하는
   사람들에게 빠른 처방이 가능합니다.


<hr />




# 2.  서비스 아키텍쳐
---
<img src="./app_system.PNG"/>

<hr />

# 3.  기대효과 

-  부양자녀들이 부모님이 본인 정보 노출을 허가 한다면 부모님의 현 위치와 그동안 어디로 다니시는지
 알 수 있다  이로써 치매노인분들에게 일어날 수 있는 우발적 사고를 막을 수 있고, 빠르게 대처를 취할 수 있다.
-  의사와 약사 사이에 QR코드로 처방전을 빠르게 주고받음으로써 빠르고 간편한 진료 및 처방이 가능하다.
-  사용자들 또한 QR코드를 활용하여 본인 처방전 및 진단서를 받아볼 수 있기에 자가 진단도 가능하다.
 

<hr />


# 4.  사용된 오픈소스 

<a href="https://github.com/Haamseongho/ubinet_Server/tree/master/fb_login">
### 안드로이드 관련 사용 오픈소스 : SNS로그인 - Facebook
</a>

<br />

<a href="https://github.com/Haamseongho/ubinet_Server/tree/master/kk_login">
### 안드로이드 관련 사용 오픈소스 : SNS로그인 - Kakao
</a>


<br />


(**	 위 내용은 서버 Repository에도 존재합니다. 	**)


<br />


# 5. 사용 라이브러리

Download
--------
```

	[xml]
	<dependency>
  		<groupId>com.squareup.retrofit2</groupId>
  		<artifactId>retrofit</artifactId>
  		<version>2.3.0</version>
	</dependency>


```
or Gradle:

```

	groovy
	compile 'com.squareup.retrofit2:retrofit:2.3.0'


```

Snapshots of the development version are available in
 
[Sonatype's `snapshots` repository][snap].

Retrofit requires at minimum Java 7 or Android 2.3.


ProGuard
--------

If you are using ProGuard you might need to add the following options:
```

	-dontwarn okio.**
	-dontwarn javax.annotation.**


```


# 6.  라이센스
```


	Copyright 2013 Square, Inc.
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	   http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
	

```

