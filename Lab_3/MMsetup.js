/*
* MMsetup.js
* Max Parker and Julia Dressel
*/

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
db.createCollection("manuscript",
   { validator: { $or:
      [
         { _id: { $type: "number" } },
         { editor_id: { $type: "number" } },
         { author_id: { $type: "number" } },
         { title: { $type: "string" } },
         { status: { $type: "string" } },
         { status_time_stamp: { $type: "string" } },
         { RI_Code: { $type: "number" } }
      ]
   }
});
db.createCollection("editor",
    { validator: { $or:
      [
         { _id: { $type: "number" } },
         { first_name: { $type: "string" } },
         { last_name: { $type: "string" } },

      ]
   }
});
db.createCollection("author",
    { validator: { $or:
      [
         { _id: { $type: "number" } },
         { first_name: { $type: "string" } },
         { last_name: { $type: "string" } },
         { address: { $type: "string" } },
         { email: { $type: "string" } },
         { affiliation: { $type: "string" } }
      ]
   }
});
db.createCollection("secondary_author",
    { validator: { $or:
      [
         { manuscript_id: { $type: "number" } },
         { rank: { $type: "number" } },
         { first_name: { $type: "string" } },
         { last_name: { $type: "string" } }
      ]
   }
});
db.createCollection("issue",
    { validator: { $or:
      [
         { pub_year: { $type: "number" } },
         { period_num: { $type: "number" } },
         { total_pages: { $type: "number" } },
         { print_date: { $type: "string" } }
      ]
   }
});
db.createCollection("reviewer",
    { validator: { $or:
      [
         { _id: { $type: "number" } },
         { first_name: { $type: "string" } },
         { last_name: { $type: "string" } },
         { email: { $type: "string" } },
         { affiliation: { $type: "string" } }
      ]
   }
});
db.createCollection("RICodes",
    { validator: { $or:
      [
         { _id: { $type: "number" } },
         { interest: { $type: "string" } }
      ]
   }
});
db.createCollection("manuscript_review",
    { validator: { $or:
      [
         { manuscript_id: { $type: "number" } },
         { reviewer_id: { $type: "number" } },
         { date_assigned: { $type: "string" } },
         { appropriateness: { $type: "number" } },
         { clarity: { $type: "number" } },
         { contribution: { $type: "number" } },
         { recommendation: { $type: "string" } },
         { date_reviewed: { $type: "string" } }
      ]
   }
});

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
      _id : "editor_id",
      "seq": 0
   }
);

// counter for increasing reviewer id
db.counters.insert(
   {
      _id : "reviewer_id",
      "seq": 0
   }
);

// counter for increasing author id
db.counters.insert(
   {
      _id : "author_id",
      "seq": 0
   }
);

db.counters.insert(
   {
      _id : "manuscript_id",
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
