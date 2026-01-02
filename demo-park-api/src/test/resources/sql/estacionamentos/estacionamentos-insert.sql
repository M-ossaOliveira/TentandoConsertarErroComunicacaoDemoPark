insert into USUARIOS (id, username, password, role) values (100,'ana@email.com',
'$2a$12$Z/YMDTy3br.Cmq.yu0EpM.iUODoJVlb1ioQCe3TvD4f5pbiCrAZWi','ROLE_ADMIN');
insert into USUARIOS (id, username, password, role) values (101,'bia@email.com',
'$2a$12$Z/YMDTy3br.Cmq.yu0EpM.iUODoJVlb1ioQCe3TvD4f5pbiCrAZWi','ROLE_CLIENTE');
insert into USUARIOS (id, username, password, role) values (102,'bob@email.com',
'$2a$12$Z/YMDTy3br.Cmq.yu0EpM.iUODoJVlb1ioQCe3TvD4f5pbiCrAZWi','ROLE_CLIENTE');

insert into CLIENTES (id, nome, cpf, id_usuario) values (10, 'Bianca Silva','79074426050',101);
insert into CLIENTES (id, nome, cpf, id_usuario) values (20, 'Roberto Gomes','55352517047',102);


insert into VAGAS (id, codigo, status_vaga) values (10,'A-01','LIVRE');
insert into VAGAS (id, codigo, status_vaga) values (20,'A-02','LIVRE');
insert into VAGAS (id, codigo, status_vaga) values (30,'A-03','OCUPADA');
insert into VAGAS (id, codigo, status_vaga) values (40,'A-04','LIVRE');

insert into CLIENTES_TEM_VAGAS (numero_recibo, placa, marca, modelo, cor, data_entrada, id_cliente, id_vaga)
values ('20260102-101300','FIT-1020','FIAT','PALIO','VERDE','2026-01-02 10:15:00', 22, 100);
insert into CLIENTES_TEM_VAGAS (numero_recibo, placa, marca, modelo, cor, data_entrada, id_cliente, id_vaga)
values ('20260102-101400','SIE-1020','FIAT','SIENA','BRANCO','2026-01-02 10:15:00', 21, 200);
insert into CLIENTES_TEM_VAGAS (numero_recibo, placa, marca, modelo, cor, data_entrada, id_cliente, id_vaga)
values ('20260102-101500','FIT-1020','FIAT','PALIO','VERDE','2026-01-02 10:15:00', 22, 100);