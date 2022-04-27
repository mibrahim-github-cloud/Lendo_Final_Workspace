# Lendo_Consume_Public_API_Workspace
Have used RestTemplate client for consuming the public APIs shared. This module supports the below listed APIs.And, API's request and responses snippet have been ported in the Workspace/Request_Response_Details path .

Note: Exceprt "User Login" API all other APIs required JWT Authorization Token to get served. Else we will get "Forbidden" response error code.

## Authentication API
* User Login(http://localhost:8081/users/login) - Authentication API to get the "JWT Token" for accessing other services.

### User APIs
* Get Users (http://localhost:8081/v2/users)
* Get User's Post (http://localhost:8081/v2/users/user_posts/{userId})

### Post APIs
* Get All Posts(http://localhost:8081/v2/posts)
* Get Post's comments (http://localhost:8081/v2/posts/post_comments/{postId})

### Comments APIs
* Get All Comments(http://localhost:8081/v2/comments)
 
