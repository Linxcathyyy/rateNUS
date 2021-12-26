# backend

## Run project

```
Gradle > Tasks > application > bootRun
```

## API

* Hostel
    * Get all Hostels
        * Give: `@RequestBody Map<String, Object> jsonInput`
          ```
          {
            "orderBy": "rating",
            "isLowToHigh": false,
            "pageNum": 1,
            "pageSize": 2
          }
          ```
        * Path: `hostel`
        * Note:
            * Page number starts from 0
            * If not specified, the default values are:
              ```
              {
                "orderBy": "id",
                "isLowToHigh": true,
                "pageNum": 0,
                "pageSize": 5
              }
              ```
    * Get Hostel by Hostel's ID
        * Give: `@PathVariable("hostelId") long hostelId`
        * Path: `hostel/{hostelId}`
    * Find Hostel by keyword
        * Give: `@RequestBody Map<String, Object> jsonInput`
          ```
          {
            "keyword": "utown",
            "pageNum": 1,
            "pageSize": 2
          }
          ```
        * Path: `hostel/search`
        * Note:
            * Page number starts from 0
            * If not specified, the default values are:
            ```
            {
              "keyword": "",
              "pageNum": 0,
              "pageSize": 5
            }
            ```
* Stall
    * Get all Stalls
        * Give: `@RequestBody Map<String, Object> jsonInput`
          ```
          {
            "orderBy": "rating",
            "isLowToHigh": false,
            "pageNum": 1,
            "pageSize": 2
          }
          ```
        * Path: `stall`
        * Note:
            * Page number starts from 0
            * If not specified, the default values are:
              ```
              {
                "orderBy": "id",
                "isLowToHigh": true,
                "pageNum": 0,
                "pageSize": 5
              }
              ```
    * Get Stall by Stall's ID
        * Give: `@PathVariable("stallId") long stallId`
        * Path: `stall/{stallId}`
    * Find Stall by keyword
        * Give: `@RequestBody Map<String, Object> jsonInput`
          ```
          {
            "keyword": "stall 1",
            "pageNum": 1,
            "pageSize": 2
          }
          ```
        * Path: `stall/search`
        * Note:
            * Page number starts from 0
            * If not specified, the default values are:
            ```
            {
              "keyword": "",
              "pageNum": 0,
              "pageSize": 5
            }
            ```
* Study Area
    * Get all Study Areas
        * Give: `@RequestBody Map<String, Object> jsonInput`
          ```
          {
            "orderBy": "rating",
            "isLowToHigh": false,
            "pageNum": 1,
            "pageSize": 2
          }
          ```
        * Path: `studyarea`
        * Note:
            * Page number starts from 0
            * If not specified, the default values are:
              ```
              {
                "orderBy": "id",
                "isLowToHigh": true,
                "pageNum": 0,
                "pageSize": 5
              }
              ```
    * Get Study Area by Study Area's ID
        * Give: `@PathVariable("studyAreaId") long studyAreaId`
        * Path: `studyarea/{studyAreaId}`
    * Find Study Area by keyword
        * Give: `@RequestBody Map<String, Object> jsonInput`
          ```
          {
            "keyword": "study area 1",
            "pageNum": 1,
            "pageSize": 2
          }
          ```
        * Path: `studyarea/search`
        * Note:
            * Page number starts from 0
            * If not specified, the default values are:
            ```
            {
              "keyword": "",
              "pageNum": 0,
              "pageSize": 5
            }
            ```
* Comment
    * Get Comment by Type and Comment's target ID
        * Give: `@PathVariable("type") Type type`, `@PathVariable("targetId") long targetId`
          , `@RequestBody Map<String, Object> jsonInput`
          ```
          {
            "orderBy": "rating",
            "isLowToHigh": true,
            "pageNum": 1,
            "pageSize": 2
          }
          ```
        * Path: `comment/{type}/{targetId}`
        * Note:
            * Page number starts from 0
            * If not specified, the default values are:
              ```
              {
                "orderBy": "timestamp",
                "isLowToHigh": false,
                "pageNum": 0,
                "pageSize": 5
              }
              ```
    * Get Comment by user ID
        * Give: `@PathVariable("userId") long userId`
          , `@RequestBody Map<String, Object> jsonInput`
          ```
          {
            "orderBy": "rating",
            "isLowToHigh": true
          }
          ```
        * Path: `comment/user/{userId}`
        * Note:
            * Page number starts from 0
            * If not specified, the default values are:
              ```
              {
                "orderBy": "timestamp",
                "isLowToHigh": false
              }
              ```
    * Add Comment
        * Header: `Authorization` header needed with value: `"Bearer " + jwtToken`
        * Give: `@RequestBody Comment comment`
        * Path: `comment`
            * role == "USER": `comment`
    * Edit Comment
        * Header: `Authorization` header needed with value: `"Bearer " + jwtToken`
        * Give: `@PathVariable("commentId") long commentId`, `@RequestBody Map<String, Object> jsonInput`, `@RequestParam(name = "token") String token`, `@RequestParam(name = "username") String username`
          ```
          {
            "text": "updated comment message"
            "rating": 1
          }
          ```
        * Path: `comment/{commentId}`
        * Note:
            * Partial json is accepted (i.e. if only contains `text` then only `text` will be updated, same
              for `rating`)
            * If not specified, there is **no** default values! The field will just be not updated.
    * Delete Comment
        * Header: `Authorization` header needed with value: `"Bearer " + jwtToken`
        * Give: `@PathVariable("commentId") long commentId`, `@RequestParam(name = "token") String token`, `@RequestParam(name = "username") String username`
        * Path: `comment/{commentId}`
* Authentication
    * Post login request
        * Give: `@RequestBody LoginRequest loginRequest`
          ```
          {
            "username": "user1",
            "password": "Password123!"
          }
          ```
        * Path: `login`
    * Post signup request
        * Give: `@RequestBody SignupRequest signupRequest`
          ```
          {
            "username": "user",
            "email": "user.success@gmail.com",
            "password": "Password123!"
          }
          ```
        * Path: `signup`
    * Get request: confirm registration
        * Give: `@RequestParam("token") String token` with empty body
        * Path: `registrationConfirm`
    * Post request: forget password
        * Give: `@RequestBody ForgetPasswordRequest forgetPasswordRequest`
          ```
          {
            "email": "user.success@gmail.com"
          }
          ```
        * Path: `forgetPassword`
    * Post reset password request
        * Give: `@RequestParam("token") String token, @RequestBody ResetPasswordRequest resetPasswordRequest`
          ```
          {
            "password": "newPassword1!"
          }
          ```
        * Path: `resetPassword`
