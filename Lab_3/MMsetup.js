// drop the collections if they exist
db.manuscript.drop();
db.editor.drop();
db.author.drop();
db.secondary_author.drop();
db.issue.drop();
db.reviewer.drop();
db.RICodes.drop();
db.manuscript_review.drop();
db.counters.drop();

// create the collections
db.createCollection("manuscript");
db.createCollection("editor");
db.createCollection("author");
db.createCollection("secondary_author");
db.createCollection("issue");
db.createCollection("reviewer");
db.createCollection("RICodes");
db.createCollection("manuscript_review");
db.createCollection("counters");

// set up counter for increasing RI_Code id
db.counters.insert(
   {
      _id : "RI_Code",
      "seq": 0
   }
);

// counter for increasing editor id
db.counters.insert(
   {
      _id : "editor_ID",
      "seq": 0
   }
);

// counter for increasing reviewer id
db.counters.insert(
   {
      _id : "reviewer_ID",
      "seq": 0
   }
);

// counter for increasing author id
db.counters.insert(
   {
      _id : "author_ID",
      "seq": 0
   }
);

db.counters.insert(
   {
      _id : "manuscript_ID",
      "seq": 0
   }
);

// function for increasing a counter
function getNextSequence(name) {
   var ret = db.counters.findAndModify(
          {
            query: { _id: name },
            update: { $inc: { seq: 1 } },
            new: true
          }
   );

   return ret.seq;
}



// insert RICodes with iterating _id
db.RICodes.insertMany([
  {
    _id: getNextSequence("RI_Code"),
    interest:'Agricultural engineering'
    },
{
    _id: getNextSequence("RI_Code"),
    interest:'Biochemical engineering'
    },
{
    _id: getNextSequence("RI_Code"),
    interest:'Biomechanical engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Ergonomics'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Food engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Bioprocess engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Genetic engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Human genetic engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Metabolic engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Molecular engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Neural engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Protein engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Rehabilitation engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Tissue engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Aquatic and environmental engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Architectural engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Civionic engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Construction engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Earthquake engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Earth systems engineering and management'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Ecological engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Environmental engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Geomatics engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Geotechnical engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Highway engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Hydraulic engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Landscape engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Land development engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Pavement engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Railway systems engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'River engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Sanitary engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Sewage engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Structural engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Surveying'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Traffic engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Transportation engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Urban engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Irrigation and agriculture engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Explosives engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Biomolecular engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Ceramics engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Broadcast engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Building engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Signal Processing'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Computer engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Power systems engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Control engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Telecommunications engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Electronic engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Instrumentation engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Network engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Neuromorphic engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Engineering Technology'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Integrated engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Value engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Cost engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Fire protection engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Domain engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Engineering economics'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Engineering management'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Engineering psychology'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Ergonomics'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Facilities Engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Logistic engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Model-driven engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Performance engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Process engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Product Family Engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Quality engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Reliability engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Safety engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Security engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Support engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Systems engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Metallurgical Engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Surface Engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Biomaterials Engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Crystal Engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Amorphous Metals'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Metal Forming'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Ceramic Engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Plastics Engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Forensic Materials Engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Composite Materials'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Casting'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Electronic Materials'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Nano materials'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Corrosion Engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Vitreous Materials'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Welding'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Acoustical engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Aerospace engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Audio engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Automotive engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Building services engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Earthquake engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Forensic engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Marine engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Mechatronics'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Nanoengineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Naval architecture'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Sports engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Structural engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Vacuum engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Military engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Combat engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Offshore engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Optical engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Geophysical engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Mineral engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Mining engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Reservoir engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Climate engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Computer-aided engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Cryptographic engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Information engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Knowledge engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Language engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Release engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Teletraffic engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Usability engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Web engineering'},
{
    _id: getNextSequence("RI_Code"),
    interest:'Systems engineering'}
]);


// insert 10 editors with incrementing _id
db.editor.insertMany([
  {_id : getNextSequence("editor_ID"),
  first_name : "Kirsten",
  last_name : "Wilcox"},
  {_id : getNextSequence("editor_ID"),
  first_name : "Zelda",
  last_name : "Vaughan"},
  {_id : getNextSequence("editor_ID"),
  first_name : "Imelda",
  last_name : "Washington"},
  {_id : getNextSequence("editor_ID"),
  first_name : "Martin",
  last_name : "Hubbard"},
  {_id : getNextSequence("editor_ID"),
  first_name : "Chava",
  last_name : "Workman"},
  {_id : getNextSequence("editor_ID"),
  first_name : "Timon",
  last_name : "Nielsen"},
  {_id : getNextSequence("editor_ID"),
  first_name : "Iona",
  last_name : "Reid"},
  {_id : getNextSequence("editor_ID"),
  first_name : "Dorian",
  last_name : "Fuller"},
  {_id : getNextSequence("editor_ID"),
  first_name : "Lynn",
  last_name : "Castro"},
  {_id : getNextSequence("editor_ID"),
  first_name : "Carter",
  last_name : "Joyner"},
]);


// insert 10 authors
db.author.insertMany(
  [
    {
      _id : getNextSequence("author_ID"),
      first_name : "Jordan",
      last_name : "Erickson",
      address : "853-3707 In Rd.",
      email : "enim@necluctus.org",
      affiliation : "Fringilla Limited"
    },
    {
      _id : getNextSequence("author_ID"),
      first_name : "Blythe",
      last_name : "Velasquez",
      address : "293-2799 Nascetur Ave",
      email : "orci@penatibusetmagnis.org",
      affiliation : "Elit Elit Corp."
    },
    {
      _id : getNextSequence("author_ID"),
      first_name : "Candace",
      last_name : "Kinney",
      address : "279-824 Vitae, Rd.",
      email : "ac@luctusut.edu",
      affiliation : "Nunc Ullamcorper Consulting"
    },
    {
      _id : getNextSequence("author_ID"),
      first_name : "Flavia",
      last_name : "Holt",
      address : "P.O. Box 849, 3652 Morbi St.",
      email : "Nunc.quis@Lorem.edu",
      affiliation : "Ornare Lectus Institute"
    },
    {
      _id : getNextSequence("author_ID"),
      first_name : "Pearl",
      last_name : "Ball",
      address : "P.O. Box 188, 1857 A Street",
      email : "consequat@Quisque.edu",
      affiliation : "Sociis Natoque Penatibus Inc."
    },
    {
      _id : getNextSequence("author_ID"),
      first_name : "Casey",
      last_name : "Whitaker",
      address : "P.O. Box 686, 8740 Et, Av.",
      email : "Cum.sociis@posuerecubilia.com",
      affiliation : "Erat Semper Rutrum Inc."
    },
    {
      _id : getNextSequence("author_ID"),
      first_name : "Hedda",
      last_name : "Shaw",
      address : "Ap #763-2728 Tellus Av.",
      email : "nisi@vitae.ca",
      affiliation : "Malesuada Id Erat Corporation"
    },
    {
      _id : getNextSequence("author_ID"),
      first_name : "Graiden",
      last_name : "Brewer",
      address : "P.O. Box 124, 8009 Nunc Rd.",
      email : "amet.faucibus@rutrum.com",
      affiliation : "Mattis Ornare Lectus Company"
    },
    {
      _id : getNextSequence("author_ID"),
      first_name : "Fredericka",
      last_name : "Mayer",
      address : "830 Ornare Rd.",
      email : "ornare.In@scelerisque.ca",
      affiliation : "In Ltd"
    },
    {
      _id : getNextSequence("author_ID"),
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
        _id : getNextSequence("reviewer_ID"),
        first_name : "Evelyn",
        last_name : "Adams",
        email: "massa.non@ligula.edu",
        affiliation: "Eu Arcu Morbi Associates"
    },
    {
        _id : getNextSequence("reviewer_ID"),
        first_name : "Tasha",
        last_name : "Clarke",
        email: "isus.varius@consectetueradipiscingelit.org",
        affiliation: "Risus Quis Associates"
    },
    {
        _id : getNextSequence("reviewer_ID"),
        first_name : "Davis",
        last_name : "Marks",
        email: "sed.facilisis@Maecenasmalesuadafringilla.org",
        affiliation: "Mus LLC"
    },
    {
        _id : getNextSequence("reviewer_ID"),
        first_name : "Yuli",
        last_name : "Gordon",
        email: "nisi@Nullamvelitdui.ca",
        affiliation: "Malesuada PC"
    },
    {
        _id : getNextSequence("reviewer_ID"),
        first_name : "Cameron",
        last_name : "Vazquez",
        email: "vel@montesnascetur.edu",
        affiliation: "Nunc Company"
    },
    {
        _id : getNextSequence("reviewer_ID"),
        first_name : "Victoria",
        last_name : "Acevedo",
        email: "eu.lacus.Quisque@aliquetdiam.org",
        affiliation: "At Pede Incorporated"
    },
    {
        _id : getNextSequence("reviewer_ID"),
        first_name : "Brenna",
        last_name : "Morrow",
        email: "risus.In.mi@miAliquamgravida.net",
        affiliation: "Ultrices Posuere Foundation"
    },
    {
        _id : getNextSequence("reviewer_ID"),
        first_name : "Kato",
        last_name : "Serrano",
        email: "id.enim@Sedeueros.ca",
        affiliation: "Sed Industries"
    },
    {
        _id : getNextSequence("reviewer_ID"),
        first_name : "Judah",
        last_name : "Conway",
        email: "massa.rutrum.magna@Utnec.ca",
        affiliation: "Dolor Tempus Non LLC"
    },
    {
        _id : getNextSequence("reviewer_ID"),
        first_name : "Gretchen",
        last_name : "George",
        email: "porttitor.scelerisque@adipiscing.edu",
        affiliation: "Lobortis Nisi PC"
    }
]);

// insert manuscripts
db.manuscript.insertMany([
    {
        _id : getNextSequence("manuscript_ID"),
        "editor_ID": 7,
        author_ID : 4,
        title : "sed tortor.",
        status : "accepted",
        status_time_stamp : "Wed Jun 14th 2017 11:06:41",
        RI_Code : 89
    },
    {
        _id : getNextSequence("manuscript_ID"),
        "editor_ID": 10,
        author_ID : 3,
        title : "gravida nunc",
        status : "scheduled for publication",
        status_time_stamp : "Thu Dec 29th 2016 07:12:29",
        RI_Code : 84
    },
    {
        _id : getNextSequence("manuscript_ID"),
        "editor_ID": 9,
        author_ID : 10,
        title : "id",
        status : "accepted",
        status_time_stamp : "Sat Aug 6th 2016 04:08:20",
        RI_Code : 101
    },
    {
        _id : getNextSequence("manuscript_ID"),
        "editor_ID": 2,
        author_ID : 3,
        title : "nulla. Integer",
        status : "scheduled for publication",
        status_time_stamp : "Fri Dec 8th 2017 02:12:10",
        RI_Code : 82
    },
    {
        _id : getNextSequence("manuscript_ID"),
        "editor_ID": 8,
        author_ID : 6,
        title : "Proin vel",
        status : "published",
        status_time_stamp : "Sat Jun 4th 2016 04:06:32",
        RI_Code : 42
    },
    {
        _id : getNextSequence("manuscript_ID"),
        "editor_ID": 6,
        author_ID : 1,
        title : "sagittis",
        status : "published",
        status_time_stamp : "Sun Apr 1st 2018 03:04:42",
        RI_Code : 11
    },
    {
        _id : getNextSequence("manuscript_ID"),
        "editor_ID": 1,
        author_ID : 9,
        title : "hendrerit neque. In",
        status : "scheduled for publication",
        status_time_stamp : "Fri Mar 10th 2017 11:03:03",
        RI_Code : 117
    },
    {
        _id : getNextSequence("manuscript_ID"),
        "editor_ID": 4,
        author_ID : 4,
        title : "aliquet, sem ut",
        status : "under review",
        status_time_stamp : "Fri Dec 9th 2016 02:12:14",
        RI_Code : 73
    },
    {
        _id : getNextSequence("manuscript_ID"),
        "editor_ID": 7,
        author_ID : 5,
        title : "Lorem ipsum dolor",
        status : "submitted",
        status_time_stamp : "Wed Jun 21st 2017 09:06:28",
        RI_Code : 79
    },
    {
        _id : getNextSequence("manuscript_ID"),
        "editor_ID": 4,
        author_ID : 8,
        title : "dis parturient",
        status : "submitted",
        status_time_stamp : "Wed May 9th 2018 07:05:03",
        RI_Code : 46
    },
    {
        _id : getNextSequence("manuscript_ID"),
        "editor_ID": 7,
        author_ID : 6,
        title : "euismod et,",
        status : "in typesetting",
        status_time_stamp : "Fri Oct 14th 2016 09:10:51",
        RI_Code : 123
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 9,
        author_ID : 1,
        title : "quis",
        status : "under review",
        status_time_stamp : "Thu Dec 22nd 2016 08:12:18",
        RI_Code : 99
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 4,
        author_ID : 7,
        title : "Cras",
        status : "rejected",
        status_time_stamp : "Wed Dec 21st 2016 03:12:52",
        RI_Code : 64
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 6,
        author_ID : 2,
        title : "viverra. Donec tempus,",
        status : "in typesetting",
        status_time_stamp : "Tue Aug 1st 2017 09:08:50",
        RI_Code : 35
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 4,
        author_ID : 5,
        title : "Proin velit.",
        status : "published",
        status_time_stamp : "Tue Sep 6th 2016 02:09:34",
        RI_Code : 113
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 8,
        author_ID : 8,
        title : "libero nec ligula",
        status : "accepted",
        status_time_stamp : "Mon Nov 20th 2017 08:11:58",
        RI_Code : 32
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 5,
        author_ID : 3,
        title : "ac ipsum. Phasellus",
        status : "scheduled for publication",
        status_time_stamp : "Sat Oct 21st 2017 04:10:56",
        RI_Code : 34
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 10,
        author_ID : 4,
        title : "erat. Sed",
        status : "published",
        status_time_stamp : "Tue Sep 27th 2016 10:09:12",
        RI_Code : 43
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 1,
        author_ID : 3,
        title : "dui. Fusce",
        status : "in typesetting",
        status_time_stamp : "Mon Jan 15th 2018 03:01:17",
        RI_Code : 104
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 6,
        author_ID : 1,
        title : "tortor. Nunc",
        status : "under review",
        status_time_stamp : "Sun Apr 29th 2018 02:04:47",
        RI_Code : 4
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 2,
        author_ID : 10,
        title : "at",
        status : "in typesetting",
        status_time_stamp : "Tue Mar 21st 2017 09:03:14",
        RI_Code : 54
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 1,
        author_ID : 9,
        title : "nec ante. Maecenas",
        status : "scheduled for publication",
        status_time_stamp : "Wed Sep 14th 2016 04:09:44",
        RI_Code : 42
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 6,
        author_ID : 3,
        title : "ac mattis",
        status : "rejected",
        status_time_stamp : "Tue May 2nd 2017 11:05:06",
        RI_Code : 77
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 10,
        author_ID : 5,
        title : "semper tellus",
        status : "rejected",
        status_time_stamp : "Wed Dec 20th 2017 11:12:02",
        RI_Code : 46
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 7,
        author_ID : 3,
        title : "metus urna",
        status : "accepted",
        status_time_stamp : "Tue Apr 3rd 2018 05:04:40",
        RI_Code : 54
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 1,
        author_ID : 2,
        title : "ullamcorper. Duis at",
        status : "accepted",
        status_time_stamp : "Tue Jul 4th 2017 12:07:50",
        RI_Code : 32
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 5,
        author_ID : 5,
        title : "feugiat.",
        status : "accepted",
        status_time_stamp : "Sun Apr 30th 2017 10:04:45",
        RI_Code : 33
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 3,
        author_ID : 5,
        title : "auctor,",
        status : "under review",
        status_time_stamp : "Wed Apr 11th 2018 06:04:59",
        RI_Code : 68
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 3,
        author_ID : 3,
        title : "nec",
        status : "in typesetting",
        status_time_stamp : "Fri Feb 16th 2018 04:02:44",
        RI_Code : 6
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 3,
        author_ID : 3,
        title : "auctor odio",
        status : "under review",
        status_time_stamp : "Tue Dec 5th 2017 02:12:18",
        RI_Code : 48
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID : 6,
        author_ID : 9,
        title : "aliquet",
        status : "under review",
        status_time_stamp : "Sun Nov 5th 2017 01:11:22",
        RI_Code : 109
    },
    {
        _id : getNextSequence("manuscript_ID"),
        editor_ID: 7,
        author_ID : 7,
        title : "nunc",
        status : "in typesetting",
        status_time_stamp : "Wed Jun 14th 2017 08:06:22",
        RI_Code : 108
    }
]);
