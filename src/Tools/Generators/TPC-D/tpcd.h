/*****************************************************************
 *  Title: tpcd.h for TPC D
 *  Sccsid: @(#)tpcd.h	9.1.1.23 5/1/96  11:45:02
 *  Description:
 *  X
 *
 *****************************************************************
 */
#define DFLT            0x0001
#define OUTPUT          0x0002
#define EXPLAIN         0x0004
#define DBASE           0x0008
#define VERBOSE         0x0010
#define TIMING          0x0020
#define LOG             0x0040
#define QUERY           0x0080
#define REFRESH         0x0100
#define ANSI            0x0200
#define SEED            0x0400
#define COMMENT         0x0800
#define INIT            0x1000
#define TERMINATE       0x2000
#define DFLT_NUM        0x4000

/*
 * general defines
 */
#define VTAG            ':'          /* flags a variable substitution */
#define ofp             stdout       /* make the routine a filter */
#define QDIR_TAG        "DSS_QUERY"  /* variable to point to queries */
#define QDIR_DFLT       "."          /* and its default */

/*
 * database portability defines
 */
#ifdef DB2
#define GEN_QUERY_PLAN  "SET CURRENT EXPLAIN SNAPSHOT ON;"
#define START_TRAN      ""
#define END_TRAN        "COMMIT WORK;"
#define SET_OUTPUT      ""
#define SET_ROWCOUNT    "--#SET ROWS_FETCH %d\n"
#define SET_DBASE       "CONNECT TO %s ;\n"
#endif

#ifdef INFORMIX
#define GEN_QUERY_PLAN  "SET EXPLAIN ON;"
#define START_TRAN      "BEGIN WORK;"
#define END_TRAN        "COMMIT WORK;"
#define SET_OUTPUT      "OUTPUT TO "
#define SET_ROWCOUNT    "FIRST %d"
#define SET_DBASE       "database %s ;\n"
#endif

#ifdef 	SQLSERVER
#define GEN_QUERY_PLAN  "set showplan on\nset noexec on\ngo\n"
#define START_TRAN      "begin transaction\ngo\n"
#define END_TRAN        "commit transaction\ngo\n"
#define SET_OUTPUT      ""
#define SET_ROWCOUNT    "set rowcount %d\ngo\n\n"
#define SET_DBASE       "use %s\ngo\n"
#endif

#ifdef 	SYBASE
#define GEN_QUERY_PLAN  "set showplan on\nset noexec on\ngo\n"
#define START_TRAN      "begin transaction\ngo\n"
#define END_TRAN        "commit transaction\ngo\n"
#define SET_OUTPUT      ""
#define SET_ROWCOUNT    "set rowcount %d\ngo\n\n"
#define SET_DBASE       "use %s\ngo\n"
#endif

#ifdef TDAT
#define GEN_QUERY_PLAN  "EXPLAIN"
#define START_TRAN      "BEGIN TRANSACTION"
#define END_TRAN        "END TRANSACTION"
#define SET_OUTPUT      ".SET FORMAT OFF\n.EXPORT REPORT file="
#define SET_ROWCOUNT    ".SET RETCANCEL ON\n.SET RETLIMIT %d\n"
#define SET_DBASE       ".LOGON %s\n"
#endif

#define MAX_VARS      8 /* max number of host vars in any query */
#define QLEN_MAX   2048 /* max length of any query */
#define QUERIES_PER_SET 17
#define MAX_PIDS 50

EXTERN int flags;
EXTERN int s_cnt;
EXTERN char *osuff;
EXTERN int stream;
EXTERN char *lfile;
EXTERN char *ifile;
EXTERN char *tfile;

#define MAX_PERMUTE     100
#ifdef DECLARER
int rowcnt_dflt[QUERIES_PER_SET + 1] = 
    {-1,-1,100,10,-1,-1,-1,-1,-1,-1,20,-1,-1,-1,-1,-1,-1,-1};
int rowcnt;
int permute[MAX_PERMUTE][QUERIES_PER_SET] =
{
{1,4,15,10,11,6,2,16,14,8,12,17,3,5,13,7,9},
{8,2,10,14,12,17,9,3,13,6,15,11,4,1,16,5,7},
{13,15,6,4,12,14,10,1,9,17,3,7,11,5,2,8,16},
{7,3,11,17,1,16,10,9,6,8,4,2,15,5,13,12,14},
{6,11,9,1,14,15,16,3,2,12,17,8,10,7,13,5,4},
{5,7,8,12,13,17,9,3,10,11,6,4,2,1,16,14,15},
{11,9,14,10,17,12,16,2,4,13,3,1,6,5,8,7,15},
{5,4,8,17,13,2,14,9,7,11,3,15,10,16,6,1,12},
{8,3,12,5,10,11,2,4,7,15,1,14,16,17,6,9,13},
{8,4,1,10,3,9,5,16,2,7,6,13,12,11,17,14,15},
{7,10,16,12,4,13,17,3,15,8,9,11,14,6,2,5,1},
{9,16,13,14,15,4,7,8,1,6,11,17,5,10,12,3,2},
{16,13,14,1,9,8,2,12,7,15,11,4,17,10,5,3,6},
{9,4,10,6,12,17,8,11,16,5,2,1,14,13,3,7,15},
{15,14,6,8,13,12,3,11,17,1,2,16,9,5,4,7,10},
{7,5,13,4,15,14,8,3,2,1,9,17,10,16,6,11,12},
{12,4,17,3,6,7,2,8,14,15,16,11,13,10,9,1,5},
{5,7,14,2,16,11,12,13,6,17,3,4,10,9,15,1,8},
{3,11,7,13,10,9,5,8,12,14,15,6,2,1,16,17,4},
{10,15,14,7,5,8,9,12,4,11,17,1,13,3,6,16,2},
{16,1,6,13,11,5,4,15,12,8,14,2,3,10,17,7,9},
{12,15,13,5,14,3,1,9,2,6,16,11,8,4,10,17,7},
{2,11,8,5,9,14,15,16,10,4,6,13,3,1,7,17,12},
{5,1,3,6,15,9,2,12,13,14,11,7,10,17,16,4,8},
{2,11,1,16,3,8,10,13,15,14,9,4,5,6,12,17,7},
{12,3,11,7,16,5,4,9,15,17,2,13,10,6,1,14,8},
{3,5,2,12,1,7,14,11,4,16,10,6,8,9,15,17,13},
{5,8,15,10,14,12,13,16,1,4,3,9,11,17,6,2,7},
{13,3,15,4,1,14,5,10,12,2,11,16,8,6,9,17,7},
{15,2,9,10,6,8,14,4,3,11,17,5,1,7,13,12,16},
{10,2,17,7,9,6,15,12,14,16,11,4,8,3,5,1,13},
{8,5,2,10,12,7,3,6,17,14,16,4,15,9,11,1,13},
{5,16,10,1,6,14,9,4,3,17,7,12,2,11,8,15,13},
{16,17,13,6,15,10,1,2,4,14,3,11,7,8,5,12,9},
{10,5,15,8,4,6,11,12,9,1,13,2,16,14,17,3,7},
{5,11,9,8,16,15,4,2,17,12,7,14,10,13,3,6,1},
{4,15,3,1,8,5,6,12,2,14,17,11,16,13,10,7,9},
{13,7,2,4,6,9,14,3,1,16,17,12,5,11,15,8,10},
{10,16,11,13,8,7,2,9,4,12,14,15,1,6,5,17,3},
{10,12,13,8,3,11,16,1,2,14,6,4,7,5,15,17,9},
{14,9,8,12,13,6,7,10,16,5,3,15,2,17,11,1,4},
{8,12,16,13,15,7,3,2,17,5,11,1,6,14,4,10,9},
{10,3,9,15,8,2,16,4,11,7,6,12,5,1,17,14,13},
{3,16,9,7,15,4,2,1,6,12,11,10,13,14,5,8,17},
{16,17,15,12,3,9,1,13,8,14,5,7,4,11,6,10,2},
{8,12,15,6,3,14,13,17,1,11,5,2,4,9,7,16,10},
{11,16,17,14,10,15,6,2,5,7,12,1,3,4,9,8,13},
{8,12,13,17,6,16,14,5,1,9,7,10,4,15,2,3,11},
{3,2,9,6,15,5,4,7,11,13,12,14,10,8,16,17,1},
{9,3,14,13,7,17,15,12,5,1,8,11,10,16,4,2,6},
{8,7,14,3,6,15,5,2,13,1,10,12,9,11,4,17,16},
{3,13,1,10,8,5,16,14,2,17,12,7,9,6,15,4,11},
{9,7,1,3,6,13,17,11,5,4,10,14,8,12,15,16,2},
{8,1,10,13,16,3,4,14,17,11,12,2,15,5,9,7,6},
{6,14,12,17,16,11,13,9,4,8,10,3,5,2,15,1,7},
{8,17,6,10,11,15,12,3,16,7,1,4,14,2,5,13,9},
{12,13,17,11,6,10,2,1,14,9,7,15,3,5,8,16,4},
{11,2,15,6,9,4,3,12,1,14,10,13,17,7,16,5,8},
{10,5,4,9,2,17,15,6,1,14,3,16,12,11,7,8,13},
{16,10,13,9,8,2,14,5,6,7,17,3,1,15,12,4,11},
{1,3,6,9,16,15,13,11,7,4,8,5,10,14,12,2,17},
{9,14,17,15,16,4,5,3,10,1,13,2,6,11,7,8,12},
{1,3,7,11,16,6,5,9,10,4,15,13,12,8,17,2,14},
{3,16,13,1,15,4,11,7,8,5,9,2,14,10,17,12,6},
{17,3,7,8,6,2,12,15,16,10,14,11,5,4,9,1,13},
{12,15,3,16,5,11,6,10,2,8,4,7,1,13,9,14,17},
{5,4,13,2,16,3,6,15,1,7,14,8,17,9,11,12,10},
{13,1,15,8,2,4,16,11,14,5,12,9,6,3,7,10,17},
{8,1,11,7,17,15,12,5,13,10,14,3,2,4,9,16,6},
{8,3,10,4,13,12,11,1,7,2,9,17,16,15,14,6,5},
{16,9,12,14,17,7,3,6,8,5,11,2,15,1,10,13,4},
{6,9,15,1,7,4,17,8,14,11,13,12,10,16,2,5,3},
{9,10,12,6,17,2,8,7,1,13,16,5,15,14,11,3,4},
{16,4,12,10,5,3,13,11,17,14,15,7,1,2,9,6,8},
{7,14,2,17,6,3,13,12,9,11,10,8,15,5,1,4,16},
{12,7,1,4,14,9,13,16,8,6,10,2,17,11,3,15,5},
{17,10,1,9,3,11,13,4,8,2,12,16,7,15,14,6,5},
{3,17,5,13,12,8,2,4,7,11,15,1,16,14,9,6,10},
{9,8,17,4,7,15,16,13,12,11,3,2,14,1,5,6,10},
{4,14,13,10,6,9,7,2,11,3,16,17,12,1,5,8,15},
{4,2,9,12,6,1,3,17,16,11,7,10,15,14,8,13,5},
{9,15,6,3,11,8,17,1,7,12,14,4,13,16,5,10,2},
{8,11,10,2,9,16,5,6,4,17,14,15,7,1,3,13,12},
{8,1,4,12,3,9,7,10,5,11,13,15,16,2,14,6,17},
{11,9,5,6,10,15,7,1,3,8,12,2,17,4,13,16,14},
{15,4,2,7,10,6,8,13,17,14,11,16,12,9,1,5,3},
{5,8,7,15,14,9,12,10,2,3,17,11,16,4,6,13,1},
{11,16,1,5,14,17,2,9,7,4,6,12,15,10,3,13,8},
{15,13,1,3,6,9,12,14,17,10,16,4,11,5,7,8,2},
{14,8,3,5,11,7,6,16,4,13,9,12,10,17,15,1,2},
{8,11,14,15,13,6,5,3,16,2,12,7,4,9,10,17,1},
{15,1,2,8,5,14,3,12,17,4,10,6,11,9,13,7,16},
{8,12,14,10,1,5,7,16,11,9,4,2,3,13,6,15,17},
{8,13,11,2,10,7,3,12,4,6,14,9,5,15,17,16,1},
{6,12,11,3,10,9,16,1,5,4,13,8,7,2,14,17,15},
{5,6,11,14,10,9,7,17,8,1,16,12,15,3,4,13,2},
{8,1,12,4,13,14,5,11,17,6,7,16,9,3,10,2,15},
{16,13,11,4,5,3,7,6,17,15,1,8,9,14,12,10,2},
{7,12,16,14,13,10,15,8,9,4,6,3,17,2,11,5,1},
{11,6,12,9,8,4,10,1,15,2,14,16,17,13,7,3,5}
};
#define SEQUENCE(stream, query) permute[stream % MAX_PERMUTE][query - 1]
#else
extern int rowcnt_dflt[];
extern int rowcnt;
extern int *permute;
#endif
