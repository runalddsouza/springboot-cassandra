create keyspace if not exists products
WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 1};

CREATE TYPE products.url (
type text,
link text
);

CREATE TABLE IF NOT EXISTS products.electronics (
   id text,
   name text,
   code text,
   type text,
   brand text,
   links list<frozen<url>>,
   details map<text,text>,
   PRIMARY KEY (id, code)
);

insert into products.electronics(id,name,code,type,brand,links,details)
values ('uuid-1','name-1','code-1','type-1','brand-1',
[{type:'link-type-1',link:'link-1'}],
{
'key1':'value1',
'key2':'value2'
});

insert into products.electronics(id,name,code,type,brand,links,details)
values ('uuid-2','name-2','code-2','type-2','brand-2',
[{type:'link-type-1',link:'link-1'},{type:'link-type-2',link:'link-2'}],
{
'key1':'value1',
'key2':'value2'
});