create keyspace if not exists products
WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 1};

CREATE TABLE IF NOT EXISTS products.electronics (
    id text,
    name text,
    code text,
    type text,
    PRIMARY KEY (id,code)
);

INSERT into products.electronics(id,name,code,type) values ('uuid-1','name-1','code-1','type-1');
INSERT into products.electronics(id,name,code,type) values ('uuid-2','name-2','code-2','type-2');