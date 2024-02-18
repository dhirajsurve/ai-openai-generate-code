# Simple Spring AI

This project contains a web service that will accept HTTP GET requests at
`http://localhost:8080/ai/simple`.

There is optional `message` parameter whose default value is "Write program to add 2 numbers ".

The response to the request is from the OpenAI ChatGPT Service
and it will extract code from the repsonse and save it to new file 

## Prerequisite

Before using the AI commands, make sure you have a developer token from OpenAI.

Create an account at [OpenAI Signup](https://platform.openai.com/signup) and generate the token at [API Keys](https://platform.openai.com/account/api-keys).

The Spring AI project defines a configuration property named `spring.ai.openai.api-key` that you should set to the value of the `API Key` obtained from `openai.com`.

Exporting an environment variable is one way to set that configuration property.
```shell
export SPRING_AI_OPENAI_API_KEY=<INSERT KEY HERE>
```
```
you can add in springboot app setting
--spring.ai.openai.api-key=<secret-key>
```
## Building and running

```
./mvnw spring-boot:run
```

## Access the endpoint

To get a response to the default request of "Write program to add 2 numbers"

```shell 
curl http://localhost:8080/ai/simple
```

A sample response is 

``` Here's a simple Java code to add two numbers together:

```java
public class AddTwoNumbers {
    public static void main(String[] args) {
        int num1 = 5;
        int num2 = 10;
        
        int sum = num1 + num2;
        
        System.out.println("The sum of " + num1 + " and " + num2 + " is: " + sum);
    }
}

Now using the `message` request parameter
```shell
curl --get  --data-urlencode 'message=Write program to add 2 numbers.' http://localhost:8080/ai/simple 
```

A sample response is

```
Here's a simple Java code to add two numbers together:

```java
public class AddTwoNumbers {
    public static void main(String[] args) {
        int num1 = 5;
        int num2 = 10;
        
        int sum = num1 + num2;
        
        System.out.println("The sum of " + num1 + " and " + num2 + " is: " + sum);
    }
}
```

Alternatively use the `httpie` clinet
```shell
http 
```
