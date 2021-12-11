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
            "pageNum": 1
          }
          ```
        * Path: `hostel`
        * Note:
            * Page number starts from 1
            * Exception will be thrown for out of bound pages (e.g. pg 0)
    * Get All Hostels, sorted by rating
        * Give: `@PathVariable("isHighToLow") boolean isHighToLow`, `@RequestBody Map<String, Integer> jsonInput`
          ```
          {
            "pageNum": 1
          }
          ```
        * Path: `hostel/isHighToLow/{isHighToLow}`
    * Get Hostel by Hostel's ID
        * Give: `@PathVariable("hostelId") long hostelId`
        * Path: `hostel/{hostelId}`
    * Find Hostel by keyword
        * Give: `@RequestBody Map<String, String> jsonInput`
          ```
          {
            "keyword": "utown"
          }
          ```
        * Path: `hostel/search`
* Comment
    * Get Comment by Type and Comment's target ID
        * Give: `@PathVariable("type") Type type`, `@PathVariable("targetId") long targetId`
        * Path: `comment/{type}/{targetId}`
    * Get Comment by Type and Comment's target ID, sorted by rating
        * Give: `@PathVariable("type") Type type`, `@PathVariable("targetId") long targetId`
          , `@PathVariable("isHighToLow") boolean isHighToLow`
        * Path: `comment/{type}/{targetId}/{isHighToLow}`
    * Add Comment
        * Give: `@RequestBody Comment comment`
        * Path: `comment`
