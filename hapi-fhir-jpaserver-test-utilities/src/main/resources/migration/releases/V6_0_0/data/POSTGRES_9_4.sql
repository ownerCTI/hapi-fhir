INSERT INTO HFJ_RESOURCE (
   RES_ID,
   RES_VERSION,
   HAS_TAGS,
   RES_PUBLISHED,
   RES_UPDATED,
   SP_HAS_LINKS,
   HASH_SHA256,
   SP_INDEX_STATUS,
   SP_CMPSTR_UNIQ_PRESENT,
   SP_COORDS_PRESENT,
   SP_DATE_PRESENT,
   SP_NUMBER_PRESENT,
   SP_QUANTITY_NRML_PRESENT,
   SP_QUANTITY_PRESENT,
   SP_STRING_PRESENT,
   SP_TOKEN_PRESENT,
   SP_URI_PRESENT,
   RES_TYPE,
   RES_VER
)
   VALUES (
   1654,
   'R4',
   false,
   '2023-06-15 09:58:42.92',
   '2023-06-15 09:58:42.92',
   'false',
   '6beed652b77f6c65d776e57341a0b5b0596ac9cfb0e8345a5a5cfbfaa59e2b62',
   1,
   false,
   false,
   false,
   false,
   true,
   false,
   false,
   true,
   true,
   'Observation',
   1
);

INSERT INTO HFJ_SPIDX_TOKEN (
   SP_ID,
   SP_MISSING,
   SP_NAME,
   RES_ID,
   RES_TYPE,
   SP_UPDATED,
   HASH_IDENTITY,
   HASH_SYS,
   HASH_SYS_AND_VALUE,
   HASH_VALUE,
   SP_SYSTEM,
   SP_VALUE
)
   VALUES (
   17,
   false,
   'type',
   1654,
   'SearchParameter',
   '2023-06-29 09:56:02.418',
   -505122241748101416,
   4662843418098322756,
   -1363967415116769274,
   -6590269416174612528,
   'http://hl7.org/fhir/search-param-type',
   'reference'
);


INSERT INTO HFJ_SPIDX_DATE (
   SP_ID,
   SP_MISSING,
   SP_NAME,
   RES_ID,
   RES_TYPE,
   SP_UPDATED,
   HASH_IDENTITY,
   SP_VALUE_HIGH,
   SP_VALUE_HIGH_DATE_ORDINAL,
   SP_VALUE_LOW,
   SP_VALUE_LOW_DATE_ORDINAL

) VALUES (
   1,
   false,
   'birthdate',
   1654,
   'Patient',
   '2023-06-29 10:14:39.69',
   5247847184787287691,
   '1974-12-25 00:00:00',
   19741225,
   '1974-12-25 00:00:00',
   19741225
);


INSERT INTO HFJ_SPIDX_STRING (
   SP_ID,
   SP_MISSING,
   SP_NAME,
   RES_ID,
   RES_TYPE,
   SP_UPDATED,
   HASH_EXACT,
   HASH_IDENTITY,
   HASH_NORM_PREFIX,
   SP_VALUE_EXACT,
   SP_VALUE_NORMALIZED
) VALUES (
   1929,
   false,
   'description',
   1654,
   'SearchParameter',
   '2023-07-05 15:32:57.469',
   '2770485372932524289',
   -2891289766040777762,
   -4164313612790526467,
   '',
   ''
);

INSERT INTO BT2_JOB_INSTANCE (
   ID,
   JOB_CANCELLED,
   CMB_RECS_PROCESSED,
   CMB_RECS_PER_SEC,
   CREATE_TIME,
   CUR_GATED_STEP_ID,
   DEFINITION_ID,
   DEFINITION_VER,
   END_TIME,
   ERROR_COUNT,
   EST_REMAINING,
   PARAMS_JSON,
   PROGRESS_PCT,
   START_TIME,
   STAT,
   WORK_CHUNKS_PURGED
) VALUES (
   '00161699-bcfe-428e-9ca2-caceb9645f8a',
   false,
   0,
   0,
   '2023-07-06 14:24:10.845',
   'WriteBundleForImportStep',
   'bulkImportJob',
   1,
   '2023-07-06 14:25:11.098',
   0,
   '0ms',
   '{"jobId":"42bfa0dd-ab7b-4991-8284-e4b2902c696b","batchSize":100}',
   1,
   '2023-07-06 14:24:10.875',
   'COMPLETED',
   true
);

INSERT INTO BT2_WORK_CHUNK (
   ID,
   CREATE_TIME,
   END_TIME,
   ERROR_COUNT,
   INSTANCE_ID,
   DEFINITION_ID,
   DEFINITION_VER,
   RECORDS_PROCESSED,
   SEQ,
   START_TIME,
   STAT,
   TGT_STEP_ID
) VALUES (
   '01d26875-8d1a-4e37-b554-62a3219f009b',
   '2023-07-06 15:20:20.797',
   '2023-07-06 15:21:11.142',
   0,
   '00161699-bcfe-428e-9ca2-caceb9645f8a',
   'bulkImportJob',
   1,
   0,
   0,
   '2023-07-06 15:21:11.14',
   'COMPLETED',
   'ReadInResourcesFromFileStep'
);

INSERT INTO HFJ_RES_TAG (
   PID,
   PARTITION_DATE,
   PARTITION_ID,
   TAG_ID,
   RES_ID,
   RES_TYPE
) VALUES (
   1000,
   NULL,
   NULL,
   2,
   1654,
   'SUBSCRIPTION'
);