--  命名空间的创建  ---------------------------------------------------------------
CREATE TABLESPACE "PDBTRSFLIGHT_USERS" DATAFILE 
  'S:\DBSERVERS\ORACLE\ORADATA\ORCL\PDB_TRS\PDBTRSFLIGHT_TEMP01.DBF' SIZE 209715200
  LOGGING ONLINE PERMANENT BLOCKSIZE 8192
  EXTENT MANAGEMENT LOCAL AUTOALLOCATE DEFAULT 
  NOCOMPRESS  SEGMENT SPACE MANAGEMENT AUTO;
-----------------------------------------------------------------------------------


--  用户的创建  -------------------------------------------------------------------
CREATE USER "KELLER" IDENTIFIED BY VALUES 'asd123'
  DEFAULT TABLESPACE "PDBTRSFLIGHT_USERS"
  TEMPORARY TABLESPACE "TEMP";
-----------------------------------------------------------------------------------


--  用户的授权  -------------------------------------------------------------------
grant connect,resource to KELLER; 
grant dba to KELLER;
-----------------------------------------------------------------------------------