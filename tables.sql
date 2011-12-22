CREATE TABLE RECIPE(
	id_recipe INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	recipe VARCHAR(100),
	text VARCHAR(10000)
);

insert into RECIPE(recipe, text)
values('Recipe1', 'Some text1');

insert into RECIPE(recipe, text)
values('Recipe2', 'Some text2');

insert into RECIPE(recipe, text)
values('Recipe3', 'Some text3');


CREATE TABLE INGREDIENT(
	id_recipe INTEGER,
	ingredient VARCHAR(50),
	quantity VARCHAR(50),
	dimension VARCHAR(20)
);

ALTER TABLE INGREDIENT ADD FOREIGN KEY (id_recipe)
REFERENCES RECIPE (id_recipe) ON DELETE CASCADE;

insert into INGREDIENT(id_recipe, ingredient, quantity, dimension)
values(1, 'Ingr1', '100', 'g');

insert into INGREDIENT(id_recipe, ingredient, quantity, dimension)
values(1, 'Ingr2', '150', 'kg');

insert into INGREDIENT(id_recipe, ingredient, quantity, dimension)
values(2, 'Ingr1', '200', 'ml');


CREATE TABLE CATEGORY(
	id_recipe INTEGER,
	category VARCHAR(50)
);

ALTER TABLE CATEGORY ADD FOREIGN KEY (id_recipe)
REFERENCES RECIPE (id_recipe) ON DELETE CASCADE;

insert into CATEGORY(id_recipe, category)
values(1, 'Cat1');

insert into CATEGORY(id_recipe, category)
values(2, 'Cat2');

insert into CATEGORY(id_recipe, category)
values(2, 'Cat3');


CREATE TABLE ADVICE(
	id_recipe INTEGER,
	advice VARCHAR(100)
);

ALTER TABLE ADVICE ADD FOREIGN KEY (id_recipe)
REFERENCES RECIPE (id_recipe) ON DELETE CASCADE;

insert into ADVICE(id_recipe, advice)
values(2, 'Adv1');

insert into ADVICE(id_recipe, advice)
values(1, 'Adv2');

insert into ADVICE(id_recipe, advice)
values(2, 'Adv3');