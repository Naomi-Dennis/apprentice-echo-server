[![Build Status](https://travis-ci.com/Naomi-Dennis/apprentice-echo-server.svg?branch=master)](https://travis-ci.com/Naomi-Dennis/apprentice-echo-server)

# Echo & Http Server

## EchoServer

A simple terminal based server that accepts input from an external connection and outputs given text input. 

### Connecting to Server 

**Domain:** ec2-18-220-252-158.us-east-2.compute.amazonaws.com

**Port:** 5000

#### Example with netcat (via Terminal):
```bash 
nc ec2-18-220-252-158.us-east-2.compute.amazonaws.com 5000
```

### Usage 

Enter any text and press enter. 

```
some text
=> some text 
```

## Http Server 

A basic server that processes simple http requests. Limited support for media data and DELETE, PUT and PATCH requests. 

### Connecting to Server  

**Domain:** ec2-18-220-252-158.us-east-2.compute.amazonaws.com

**Port:** 2500

#### Valid Request Paths:

* GET /not_found
* GET /simple_get
* GET /cool_gif
* GET /get_with_body
* GET /redirect
* HEAD /simple_get
* HEAD /get_with_body
* POST /echo_body
* OPTIONS /get_with_body
* OPTIONS /method_options
* OPTIONS /method_options2

### Usage 

* Get requests can be accessed via any browser 
* Compatible with server tools such as cURL and Postman 

[Browser Example](http://ec2-18-220-252-158.us-east-2.compute.amazonaws.com:2500/cool_gif)

cURL Example: 
```bash 
curl -X OPTIONS ec2-18-220-252-158.us-east-2.compute.amazonaws.com:2500/method_options
```



#### General Response Example

```
* Trying 18.220.252.158...
* TCP_NODELAY set
* Connected to ec2-18-220-252-158.us-east-2.compute.amazonaws.com (18.220.252.158) port 2500 (#0)
> GET /redirect HTTP/1.1
> Host: ec2-18-220-252-158.us-east-2.compute.amazonaws.com:2500
> User-Agent: curl/7.64.1
> Accept: */*
>
< HTTP/1.1 301
< Location: http://ec2-18-220-252-158.us-east-2.compute.amazonaws.com:2500/simple_get
* no chunk, no close, no size. Assume close to signal end
```




## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
