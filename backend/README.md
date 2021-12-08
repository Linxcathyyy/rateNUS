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
    * Get Hostel by Hostel's ID
        * Give: `@PathVariable("hostelId") long hostelId`
        * Path: `hostel/{hostelId}`
    * Find Hostel by keyword
        * Give: `@RequestBody String keyword`
        * Path: `hostel`
* Comment
    * Get Comment by Comment's target ID
        * Give: `@PathVariable("targetId") long targetId`
        * Path: `comment/{targetId}`
    * Add Comment
        * Give: `@RequestBody Comment comment`
        * Path: `comment`