--  �����ռ�Ĵ���  ---------------------------------------------------------------
CREATE TABLESPACE "PDBTRSFLIGHT_USERS" DATAFILE 
  'S:\DBSERVERS\ORACLE\ORADATA\ORCL\PDB_TRS\PDBTRSFLIGHT_TEMP01.DBF' SIZE 209715200
  LOGGING ONLINE PERMANENT BLOCKSIZE 8192
  EXTENT MANAGEMENT LOCAL AUTOALLOCATE DEFAULT 
  NOCOMPRESS  SEGMENT SPACE MANAGEMENT AUTO;
-----------------------------------------------------------------------------------


--  �û��Ĵ���  -------------------------------------------------------------------
CREATE USER "KELLER" IDENTIFIED BY VALUES 'asd123'
  DEFAULT TABLESPACE "PDBTRSFLIGHT_USERS"
  TEMPORARY TABLESPACE "TEMP";
-----------------------------------------------------------------------------------


--  �û�����Ȩ  -------------------------------------------------------------------
grant connect,resource to KELLER; 
grant dba to KELLER;
-----------------------------------------------------------------------------------