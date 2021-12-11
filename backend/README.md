# backend

## Run project

```
Gradle > Tasks > application > bootRun
```

## API

* Hostel
    * Get All Hostels
        * Give: nothing
        * Path: `hostel`
    * Get All Hostels, sorted by rating
        * Give: `@PathVariable("isHighToLow") boolean isHighToLow`
        * Path: `hostel/{isHighToLow}`
    * Get Hostel by Hostel's ID
        * Give: `@PathVariable("hostelId") long hostelId`
        * Path: `hostel/{hostelId}`
    * Find Hostel by keyword
        * Give: `@RequestBody String keywordJson`
        * Path: `hostel`
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
        * Requires Role == "USER" to add comment
