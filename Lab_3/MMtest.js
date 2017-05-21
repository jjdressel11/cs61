// MMtest.js

// testing operations of the interface

// Editor

// assigning reviewers to a manuscript
db.manuscript_review.insert(
  {
    "manuscript_ID" : 1,
    "reviewer_ID" : 3,
    "date_assigned" : Date().toString()
  }
)

// reject a manuscript
db.manuscript.update(
    { _id : 3},
    {$set: {
      status : "rejected",
      status_time_stamp : Date().toString()
   }}
)

db.manuscript.insert(
  {
    _id : getNextSequence("manuscript_ID"),
    editor_ID : 3,
    author_ID : 5,
    title : "TITLE",
    status : "submitted",
    status_time_stamp : Date().toString(),
    RI_Code : 33
  }
)

// Author

// Reviewer
