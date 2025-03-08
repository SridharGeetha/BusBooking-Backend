SELECT name, is_identity 
FROM sys.columns 
WHERE object_id = OBJECT_ID('bus');

