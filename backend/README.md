# backend

## Run project

```
Gradle > Tasks > application > bootRun
```

## API

* Hostel
    * Get All Hostels
        * Give: `@RequestBody Map<String, Integer> jsonInput`
          ```
          {
            "pageNum": 0,
            "pageSize": 5
          }
          ```
        * Path: `hostel`
        * Note:
            * Page number starts from 0
            * If not specified, the above example will be the default value
    * Get All Hostels, sorted by rating
        * Give: `@PathVariable("isLowToHigh") boolean isLowToHigh`, `@RequestBody Map<String, Integer> jsonInput`
          ```
          {
            "pageNum": 0,
            "pageSize": 5
          }
          ```
        * Path: `hostel/isLowToHigh/{isLowToHigh}`
        * Note:
            * Page number starts from 0
            * If not specified, the above example will be the default value
    * Get Hostel by Hostel's ID
        * Give: `@PathVariable("hostelId") long hostelId`
        * Path: `hostel/{hostelId}`
    * Find Hostel by keyword
        * Give: `@RequestBody Map<String, Object> jsonInput`
          ```
          {
            "keyword": "utown",
            "pageNum": 0,
            "pageSize": 5
          }
          ```
        * Path: `hostel/search`
        * Note:
            * Page number starts from 0
            * If not specified, the above example will be the default value, except `keyword` which will be an empty
              string <code />
* Comment
    * Get Comment by Type and Comment's target ID
        * Give: `@PathVariable("type") Type type`, `@PathVariable("targetId") long targetId`
        * Path: `comment/{type}/{targetId}`
    * Get Comment by Type and Comment's target ID, sorted by rating
        * Give: `@PathVariable("type") Type type`, `@PathVariable("targetId") long targetId`
          , `@PathVariable("isLowToHigh") boolean isLowToHigh`
        * Path: `comment/{type}/{targetId}/{isLowToHigh}`
    * Add Comment
        * Give: `@RequestBody Comment comment`
        * Path:
            * role == "USER": `comment`
            * role != "USER": `login`
