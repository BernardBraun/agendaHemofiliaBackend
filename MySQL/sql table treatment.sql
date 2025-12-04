create table treatment(
    id int auto_increment,
    title varchar(50) null,
    dosage varchar(50) not null,
    description varchar(30) null,
    unit_measure varchar(5) not null,
    constraint pk_treatment primary key(id)
);

insert into treatment(title, dosage, description, unit_measure)
values
('ACETATO DE DESMOPRESSINA', '4 microgramas', 'Frasco-ampola', 'ml'),
('ACETATO DE DESMOPRESSINA', '15 microgramas', 'Frasco-ampola', 'ml'),
('ACEDO TRANEXÂMICO', '250 miligramas', 'Comprimido', 'mg'),
('CONCENTRADO DE FVIII RECOMBINATNE', '250', 'com apresentação', 'ui'),
('CONCENTRADO DE FVIII RECOMBINATNE', '500', 'com apresentação', 'ui'),
('CONCENTRADO DE FVIII RECOMBINATNE', '1000', 'com apresentação', 'ui'),
('CONCENTRADO DE FVIII RECOMBINATNE', '1500', 'com apresentação', 'ui'),
('CONCENTRADO DE FVIII PLASMÁTICO', '250', null, 'ui'),
('CONCENTRADO DE FVIII PLASMÁTICO', '500', null, 'ui'),
('CONCENTRADO DE FVIII PLASMÁTICO', '1000', null, 'ui'),
('CONCENTRADO DE FIX', '200', null, 'ui'),
('CONCENTRADO DE FIX', '600', null, 'ui'),
('CONCENTRADO DE FIX', '1200', null, 'ui'),
('CONCENTRADO DE FVIII DE COAGULAÇÃO', '500', 'fator de Von Willebrand', 'ui'),
('CONCENTRADO DE FVIII DE COAGULAÇÃO', '1000', 'fator de Von Willebrand', 'ui'),
('CONCENTRADO DE FATOR VII ATIVADO RECOMBINANTE', '50', null, 'kui'),
('CONCENTRADO DE FATOR VII ATIVADO RECOMBINANTE', '100', null, 'kui'),
('CONCENTRADO DE FATOR VII ATIVADO RECOMBINANTE', '520', null, 'kui'),
('COMPLEXO PROTOMBÍNICO PARCIALMENTE ATIVADO', '500', null, 'ui'),
('COMPLEXO PROTOMBÍNICO PARCIALMENTE ATIVADO', '1000', null, 'ui'),
('COMPLEXO PROTOMBÍNICO PARCIALMENTE ATIVADO', '2500', null, 'ui'),
('COMPLEXO PROTOMBÍNICO', '500', null, 'ui'),
('CONCENTRADO DE FIBRINOGENIO', '1000', 'Fator I - 1000mg/50ml', 'mg'),
('CONCENTRADO DE FXIII', '250', null, 'ui'),
('EMICIZUMABE', '30', '30mg/1ml', 'mg'),
('EMICIZUMABE', '60', '60mg/0,4ml', 'mg'),
('EMICIZUMABE', '105', '105mg/0,7ml', 'mg'),
('EMICIZUMABE', '150', '150mg/1ml', 'mg');

alter table diary
   drop column treatment;

alter table diary
 add column treatment_id int not null;

alter table diary
   add constraint fk_diary_treatment foreign key(treatment_id)
   references treatment(id);
   