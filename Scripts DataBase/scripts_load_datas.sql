--Verificador de nomes e indentificadores
select distinct nome, identificador from tab_valores_teste group by nome, identificador 
select distinct identificador from tab_valores_teste group by identificador 

--Limpeza da Tabela
delete from tab_valores_teste

--Insert da tabela indicadores
insert into tab_indicadores_teste (nome, indicador) select distinct nome, identificador from tab_valores_teste group by nome, identificador