/*
* MMtest.js
* Max Parker and Julia Dressel
*/

// Insert data into the database

// insert 10 editors with incrementing _id
db.editor.insertMany([
  {_id : getNextSequence("editor_id"),
  first_name : "Kirsten",
  last_name : "Wilcox"},
  {_id : getNextSequence("editor_id"),
  first_name : "Zelda",
  last_name : "Vaughan"},
  {_id : getNextSequence("editor_id"),
  first_name : "Imelda",
  last_name : "Washington"},
  {_id : getNextSequence("editor_id"),
  first_name : "Martin",
  last_name : "Hubbard"},
  {_id : getNextSequence("editor_id"),
  first_name : "Chava",
  last_name : "Workman"},
  {_id : getNextSequence("editor_id"),
  first_name : "Timon",
  last_name : "Nielsen"},
  {_id : getNextSequence("editor_id"),
  first_name : "Iona",
  last_name : "Reid"},
  {_id : getNextSequence("editor_id"),
  first_name : "Dorian",
  last_name : "Fuller"},
  {_id : getNextSequence("editor_id"),
  first_name : "Lynn",
  last_name : "Castro"},
  {_id : getNextSequence("editor_id"),
  first_name : "Carter",
  last_name : "Joyner"},
]);


// insert 10 authors
db.author.insertMany(
  [
    {
      _id : getNextSequence("author_id"),
      first_name : "Jordan",
      last_name : "Erickson",
      address : "853-3707 In Rd.",
      email : "enim@necluctus.org",
      affiliation : "Fringilla Limited"
    },
    {
      _id : getNextSequence("author_id"),
      first_name : "Blythe",
      last_name : "Velasquez",
      address : "293-2799 Nascetur Ave",
      email : "orci@penatibusetmagnis.org",
      affiliation : "Elit Elit Corp."
    },
    {
      _id : getNextSequence("author_id"),
      first_name : "Candace",
      last_name : "Kinney",
      address : "279-824 Vitae, Rd.",
      email : "ac@luctusut.edu",
      affiliation : "Nunc Ullamcorper Consulting"
    },
    {
      _id : getNextSequence("author_id"),
      first_name : "Flavia",
      last_name : "Holt",
      address : "P.O. Box 849, 3652 Morbi St.",
      email : "Nunc.quis@Lorem.edu",
      affiliation : "Ornare Lectus Institute"
    },
    {
      _id : getNextSequence("author_id"),
      first_name : "Pearl",
      last_name : "Ball",
      address : "P.O. Box 188, 1857 A Street",
      email : "consequat@Quisque.edu",
      affiliation : "Sociis Natoque Penatibus Inc."
    },
    {
      _id : getNextSequence("author_id"),
      first_name : "Casey",
      last_name : "Whitaker",
      address : "P.O. Box 686, 8740 Et, Av.",
      email : "Cum.sociis@posuerecubilia.com",
      affiliation : "Erat Semper Rutrum Inc."
    },
    {
      _id : getNextSequence("author_id"),
      first_name : "Hedda",
      last_name : "Shaw",
      address : "Ap #763-2728 Tellus Av.",
      email : "nisi@vitae.ca",
      affiliation : "Malesuada Id Erat Corporation"
    },
    {
      _id : getNextSequence("author_id"),
      first_name : "Graiden",
      last_name : "Brewer",
      address : "P.O. Box 124, 8009 Nunc Rd.",
      email : "amet.faucibus@rutrum.com",
      affiliation : "Mattis Ornare Lectus Company"
    },
    {
      _id : getNextSequence("author_id"),
      first_name : "Fredericka",
      last_name : "Mayer",
      address : "830 Ornare Rd.",
      email : "ornare.In@scelerisque.ca",
      affiliation : "In Ltd"
    },
    {
      _id : getNextSequence("author_id"),
      first_name : "Mercedes",
      last_name : "Levy",
      address : "244-1307 Magna Ave",
      email : "in.faucibus@quisaccumsanconvallis.com",
      affiliation : "Mauris Industries"
    }
  ]);

// insert reviewers
db.reviewer.insertMany([
    {
        _id : getNextSequence("reviewer_id"),
        first_name : "Evelyn",
        last_name : "Adams",
        email: "massa.non@ligula.edu",
        affiliation: "Eu Arcu Morbi Associates"
    },
    {
        _id : getNextSequence("reviewer_id"),
        first_name : "Tasha",
        last_name : "Clarke",
        email: "isus.varius@consectetueradipiscingelit.org",
        affiliation: "Risus Quis Associates"
    },
    {
        _id : getNextSequence("reviewer_id"),
        first_name : "Davis",
        last_name : "Marks",
        email: "sed.facilisis@Maecenasmalesuadafringilla.org",
        affiliation: "Mus LLC"
    },
    {
        _id : getNextSequence("reviewer_id"),
        first_name : "Yuli",
        last_name : "Gordon",
        email: "nisi@Nullamvelitdui.ca",
        affiliation: "Malesuada PC"
    },
    {
        _id : getNextSequence("reviewer_id"),
        first_name : "Cameron",
        last_name : "Vazquez",
        email: "vel@montesnascetur.edu",
        affiliation: "Nunc Company"
    },
    {
        _id : getNextSequence("reviewer_id"),
        first_name : "Victoria",
        last_name : "Acevedo",
        email: "eu.lacus.Quisque@aliquetdiam.org",
        affiliation: "At Pede Incorporated"
    },
    {
        _id : getNextSequence("reviewer_id"),
        first_name : "Brenna",
        last_name : "Morrow",
        email: "risus.In.mi@miAliquamgravida.net",
        affiliation: "Ultrices Posuere Foundation"
    },
    {
        _id : getNextSequence("reviewer_id"),
        first_name : "Kato",
        last_name : "Serrano",
        email: "id.enim@Sedeueros.ca",
        affiliation: "Sed Industries"
    },
    {
        _id : getNextSequence("reviewer_id"),
        first_name : "Judah",
        last_name : "Conway",
        email: "massa.rutrum.magna@Utnec.ca",
        affiliation: "Dolor Tempus Non LLC"
    },
    {
        _id : getNextSequence("reviewer_id"),
        first_name : "Gretchen",
        last_name : "George",
        email: "porttitor.scelerisque@adipiscing.edu",
        affiliation: "Lobortis Nisi PC"
    }
]);

// insert manuscripts
db.manuscript.insertMany([
    {
        _id : getNextSequence("manuscript_id"),
        "editor_id": 7,
        author_id : 4,
        title : "sed tortor.",
        status : "accepted",
        status_time_stamp : "Wed Jun 14th 2017 11:06:41",
        RI_Code : 89
    },
    {
        _id : getNextSequence("manuscript_id"),
        "editor_id": 10,
        author_id : 3,
        title : "gravida nunc",
        status : "scheduled for publication",
        status_time_stamp : "Thu Dec 29th 2016 07:12:29",
        RI_Code : 84
    },
    {
        _id : getNextSequence("manuscript_id"),
        "editor_id": 9,
        author_id : 10,
        title : "id",
        status : "accepted",
        status_time_stamp : "Sat Aug 6th 2016 04:08:20",
        RI_Code : 101
    },
    {
        _id : getNextSequence("manuscript_id"),
        "editor_id": 2,
        author_id : 3,
        title : "nulla. Integer",
        status : "scheduled for publication",
        status_time_stamp : "Fri Dec 8th 2017 02:12:10",
        RI_Code : 82
    },
    {
        _id : getNextSequence("manuscript_id"),
        "editor_id": 8,
        author_id : 6,
        title : "Proin vel",
        status : "published",
        status_time_stamp : "Sat Jun 4th 2016 04:06:32",
        RI_Code : 42
    },
    {
        _id : getNextSequence("manuscript_id"),
        "editor_id": 6,
        author_id : 1,
        title : "sagittis",
        status : "published",
        status_time_stamp : "Sun Apr 1st 2018 03:04:42",
        RI_Code : 11
    },
    {
        _id : getNextSequence("manuscript_id"),
        "editor_id": 1,
        author_id : 9,
        title : "hendrerit neque. In",
        status : "scheduled for publication",
        status_time_stamp : "Fri Mar 10th 2017 11:03:03",
        RI_Code : 117
    },
    {
        _id : getNextSequence("manuscript_id"),
        "editor_id": 4,
        author_id : 4,
        title : "aliquet, sem ut",
        status : "under review",
        status_time_stamp : "Fri Dec 9th 2016 02:12:14",
        RI_Code : 73
    },
    {
        _id : getNextSequence("manuscript_id"),
        "editor_id": 7,
        author_id : 5,
        title : "Lorem ipsum dolor",
        status : "submitted",
        status_time_stamp : "Wed Jun 21st 2017 09:06:28",
        RI_Code : 79
    },
    {
        _id : getNextSequence("manuscript_id"),
        "editor_id": 4,
        author_id : 8,
        title : "dis parturient",
        status : "submitted",
        status_time_stamp : "Wed May 9th 2018 07:05:03",
        RI_Code : 46
    },
    {
        _id : getNextSequence("manuscript_id"),
        "editor_id": 7,
        author_id : 6,
        title : "euismod et,",
        status : "in typesetting",
        status_time_stamp : "Fri Oct 14th 2016 09:10:51",
        RI_Code : 123
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 9,
        author_id : 1,
        title : "quis",
        status : "under review",
        status_time_stamp : "Thu Dec 22nd 2016 08:12:18",
        RI_Code : 99
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 4,
        author_id : 7,
        title : "Cras",
        status : "rejected",
        status_time_stamp : "Wed Dec 21st 2016 03:12:52",
        RI_Code : 64
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 6,
        author_id : 2,
        title : "viverra. Donec tempus,",
        status : "in typesetting",
        status_time_stamp : "Tue Aug 1st 2017 09:08:50",
        RI_Code : 35
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 4,
        author_id : 5,
        title : "Proin velit.",
        status : "published",
        status_time_stamp : "Tue Sep 6th 2016 02:09:34",
        RI_Code : 113
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 8,
        author_id : 8,
        title : "libero nec ligula",
        status : "accepted",
        status_time_stamp : "Mon Nov 20th 2017 08:11:58",
        RI_Code : 32
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 5,
        author_id : 3,
        title : "ac ipsum. Phasellus",
        status : "scheduled for publication",
        status_time_stamp : "Sat Oct 21st 2017 04:10:56",
        RI_Code : 34
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 10,
        author_id : 4,
        title : "erat. Sed",
        status : "published",
        status_time_stamp : "Tue Sep 27th 2016 10:09:12",
        RI_Code : 43
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 1,
        author_id : 3,
        title : "dui. Fusce",
        status : "in typesetting",
        status_time_stamp : "Mon Jan 15th 2018 03:01:17",
        RI_Code : 104
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 6,
        author_id : 1,
        title : "tortor. Nunc",
        status : "under review",
        status_time_stamp : "Sun Apr 29th 2018 02:04:47",
        RI_Code : 4
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 2,
        author_id : 10,
        title : "at",
        status : "in typesetting",
        status_time_stamp : "Tue Mar 21st 2017 09:03:14",
        RI_Code : 54
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 1,
        author_id : 9,
        title : "nec ante. Maecenas",
        status : "scheduled for publication",
        status_time_stamp : "Wed Sep 14th 2016 04:09:44",
        RI_Code : 42
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 6,
        author_id : 3,
        title : "ac mattis",
        status : "rejected",
        status_time_stamp : "Tue May 2nd 2017 11:05:06",
        RI_Code : 77
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 10,
        author_id : 5,
        title : "semper tellus",
        status : "rejected",
        status_time_stamp : "Wed Dec 20th 2017 11:12:02",
        RI_Code : 46
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 7,
        author_id : 3,
        title : "metus urna",
        status : "accepted",
        status_time_stamp : "Tue Apr 3rd 2018 05:04:40",
        RI_Code : 54
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 1,
        author_id : 2,
        title : "ullamcorper. Duis at",
        status : "accepted",
        status_time_stamp : "Tue Jul 4th 2017 12:07:50",
        RI_Code : 32
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 5,
        author_id : 5,
        title : "feugiat.",
        status : "accepted",
        status_time_stamp : "Sun Apr 30th 2017 10:04:45",
        RI_Code : 33
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 3,
        author_id : 5,
        title : "auctor,",
        status : "under review",
        status_time_stamp : "Wed Apr 11th 2018 06:04:59",
        RI_Code : 68
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 3,
        author_id : 3,
        title : "nec",
        status : "in typesetting",
        status_time_stamp : "Fri Feb 16th 2018 04:02:44",
        RI_Code : 6
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 3,
        author_id : 3,
        title : "auctor odio",
        status : "under review",
        status_time_stamp : "Tue Dec 5th 2017 02:12:18",
        RI_Code : 48
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id : 6,
        author_id : 9,
        title : "aliquet",
        status : "under review",
        status_time_stamp : "Sun Nov 5th 2017 01:11:22",
        RI_Code : 109
    },
    {
        _id : getNextSequence("manuscript_id"),
        editor_id: 7,
        author_id : 7,
        title : "nunc",
        status : "in typesetting",
        status_time_stamp : "Wed Jun 14th 2017 08:06:22",
        RI_Code : 108
    }
]);

db.manuscript_review.insertMany([
    {
        manuscript_id : 8,
        reviewer_id : 10,
        date_assigned : "Sun Oct 22 2017 01:10:49",
        appropriateness : 8,
        clarity : 7,
        methodology : 6,
        contribution : 2,
        recommendation : "accept",
        date_reviewed : "Fri Jun 09 2017 12:06:14"
    },
    {
        manuscript_id : 2,
        reviewer_id : 9,
        date_assigned : "Sun Jun 25 2017 07:06:42",
        appropriateness : 4,
        clarity : 7,
        methodology : 10,
        contribution : 10,
        recommendation : "reject",
        date_reviewed : "Tue Mar 13 2018 12:03:13"
    },
    {
        manuscript_id : 9,
        reviewer_id : 1,
        date_assigned : "Thu Jan 18 2018 10:01:18",
        appropriateness : 6,
        clarity : 1,
        methodology : 5,
        contribution : 10,
        recommendation : "reject",
        date_reviewed : "Sun Feb 18 2018 09:02:58"
    },
    {
        manuscript_id : 7,
        reviewer_id : 5,
        date_assigned : "Wed Jul 13 2016 07:07:53",
        appropriateness : 10,
        clarity : 10,
        methodology : 10,
        contribution : 9,
        recommendation : "accept",
        date_reviewed : "Sat Feb 18 2017 07:02:37"
    },
    {
        manuscript_id : 1,
        reviewer_id : 9,
        date_assigned : "Sun Jul 31 2016 12:07:20",
        appropriateness : 5,
        clarity : 8,
        methodology : 1,
        contribution : 9,
        recommendation : "reject",
        date_reviewed : "Tue Oct 24 2017 04:10:17"
    },
    {
        manuscript_id : 4,
        reviewer_id : 7,
        date_assigned : "Wed Sep 13 2017 03:09:51",
        appropriateness : 6,
        clarity : 9,
        methodology : 4,
        contribution : 10,
        recommendation : "reject",
        date_reviewed : "Sun Oct 16 2016 12:10:47"
    },
    {
        manuscript_id : 1,
        reviewer_id : 6,
        date_assigned : "Tue Aug 29 2017 12:08:13",
        appropriateness : 4,
        clarity : 5,
        methodology : 1,
        contribution : 3,
        recommendation : "reject",
        date_reviewed : "Sat Mar 31 2018 01:03:51"
    },
    {
        manuscript_id : 6,
        reviewer_id : 4,
        date_assigned : "Mon Feb 12 2018 05:02:20",
        appropriateness : 6,
        clarity : 8,
        methodology : 5,
        contribution : 7,
        recommendation : "reject",
        date_reviewed : "Tue Jun 20 2017 06:06:07"
    },
    {
        manuscript_id : 4,
        reviewer_id : 1,
        date_assigned : "Sat Nov 11 2017 05:11:47",
        appropriateness : 9,
        clarity : 3,
        methodology : 9,
        contribution : 1,
        recommendation : "accept",
        date_reviewed : "Sun Sep 03 2017 02:09:48"
    },
    {
        manuscript_id : 2,
        reviewer_id : 7,
        date_assigned : "Wed Aug 24 2016 09:08:54",
        appropriateness : 4,
        clarity : 2,
        methodology : 8,
        contribution : 8,
        recommendation : "accept",
        date_reviewed : "Thu Aug 04 2016 04:08:33"
    },
    {
        manuscript_id : 6,
        reviewer_id : 3,
        date_assigned : "Sun Sep 18 2016 05:09:56",
        appropriateness : 2,
        clarity : 5,
        methodology : 5,
        contribution : 6,
        recommendation : "reject",
        date_reviewed : "Sun May 29 2016 02:05:44"
    },
    {
        manuscript_id : 4,
        reviewer_id : 10,
        date_assigned : "Thu Nov 03 2016 05:11:05",
        appropriateness : 6,
        clarity : 2,
        methodology : 5,
        contribution : 5,
        recommendation : "reject",
        date_reviewed : "Fri Mar 10 2017 06:03:09"
    },
    {
        manuscript_id : 4,
        reviewer_id : 10,
        date_assigned : "Tue Apr 10 2018 12:04:07",
        appropriateness : 9,
        clarity : 10,
        methodology : 9,
        contribution : 3,
        recommendation : "accept",
        date_reviewed : "Sat Sep 10 2016 05:09:14"
    },
    {
        manuscript_id : 10,
        reviewer_id : 1,
        date_assigned : "Mon Oct 30 2017 12:10:58",
        appropriateness : 9,
        clarity : 3,
        methodology : 2,
        contribution : 5,
        recommendation : "reject",
        date_reviewed : "Mon May 22 2017 09:05:38"
    },
    {
        manuscript_id : 10,
        reviewer_id : 5,
        date_assigned : "Thu Oct 13 2016 12:10:51",
        appropriateness : 2,
        clarity : 7,
        methodology : 8,
        contribution : 6,
        recommendation : "accept",
        date_reviewed : "Tue Feb 06 2018 11:02:21"
    },
    {
        manuscript_id : 1,
        reviewer_id : 3,
        date_assigned : "Fri Feb 24 2017 02:02:14",
        appropriateness : 3,
        clarity : 4,
        methodology : 2,
        contribution : 5,
        recommendation : "reject",
        date_reviewed : "Sat Apr 08 2017 06:04:13"
    },
    {
        manuscript_id : 2,
        reviewer_id : 3,
        date_assigned : "Fri Apr 28 2017 08:04:01",
        appropriateness : 10,
        clarity : 4,
        methodology : 1,
        contribution : 5,
        recommendation : "accept",
        date_reviewed : "Tue May 30 2017 09:05:40"
    },
    {
        manuscript_id : 6,
        reviewer_id : 7,
        date_assigned : "Tue Apr 04 2017 11:04:29",
        appropriateness : 6,
        clarity : 1,
        methodology : 10,
        contribution : 8,
        recommendation : "reject",
        date_reviewed : "Thu Nov 23 2017 12:11:46"
    }
]);


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
  {_id : 5}
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
