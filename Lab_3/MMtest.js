// MMtest.js

// Testing operations of the interface

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

// add typesetting information to manuscript
db.manuscript.update(
  {_id : 5},
  {$set : {
    num_pages : 44,
    order_in_issue : 5,
    start_page : 11,
    pub_year : 2011,
    period_num : 3
  }}
)

// Author retracting manuscript
db.manuscript.remove(
  {_id : 116}
)

// Reviewer

// adding reviewer's ratings to a manuscript
db.manuscript_review.update(
  {manuscript_ID : 1},
  {$set : {
    appropriateness : 4,
    clarity : 2,
    methodology : 8,
    contribution : 9,
    recommendation : "accept",
    date_reviewed : Date().toString()
  }}
)
