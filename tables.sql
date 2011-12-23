CREATE TABLE RECIPE(
	id_recipe INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	recipe VARCHAR(100),
	text VARCHAR(10000)
);

insert into RECIPE(recipe, text)
values('вяленый куриный рулет с беконом', 'У куриных грудок снять кожу и вырезать кости. Получится 4 филе.
 Каждое филе разделить на 2 части - большое филе и малое филе. Большие филе разрезать горизонтально на два пласта, но не дорезая до конца.
 Раскрыть филе по разрезу как книгу.Точно так же поступить с малым филе - разрезать горизонтально и раскрыть.
Все разрезанные филе положить срезами вниз. Посолить - примерно 1/4~1/3 ч ложки соли на одно филе.
Натереть чёрным перцем и специями. Специи взять по своему вкусу. Очень хорошо для рулета подходит молотая паприка.
При желании, чтобы вкус рулета был ярче и острее, смазать поверхность филе уксусом или уксусной эссенцией.
Повернуть филе разрезами вверх. К большим филе приложить малые филе, выравнивая края.По всей поверхности филе разложить нарезанный бекон.
Свернуть рулеты беконом внутрь.Замотать нитками. Поставить в холодильник на 1~3 часа.Взять решётку из духовки и просунуть между
её прутьев краешки рулетов. Проколоть рулеты поверх решётки деревянными палочками и расположить их поперёк прутьев.
Решётку с рулетами поместить на самый верхний уровень духовки.Под рулеты подставить противень, застеленный фольгой. Или можно листами фольги застелить дно духовки.
Это требуется для того, чтобы предохранить духовку от капающего жира. Температуру в духовке выставить на t=50°C.
Дверцу духовки оставить немного приоткрытой. Если есть, то включить режим вентиляции. Вялить рулеты от 4 до 5 часов.');

insert into RECIPE(recipe, text)
values('фасолевый салат с консервированной рыбой', 'Фасоль откинуть на дуршлаг и промыть кипячёной или фильтрованной водой.
Зелёный лук мелко порезать. Огурец нарезать небольшими кубиками. Рыбу вынуть из банки, выбрать кости и поломать на кусочки.
В маленькой мисочке смешать уксус, горчицу и масло. При желании можно добавить молотый перец. 
Все составляющие салата перемешать и полить заправкой. Попробовать. При необходимости посолить. ');

insert into RECIPE(recipe, text)
values('картофель дольками, запеченный с беконом', 'Картофель и морковь очистить и порезать тонкими дольками.
Если картофель и морковь очень крупные, то можно порезать полу- или четверть дольками.
Порезанный картофель промыть от крахмала в холодной воде. Лук порезать тонкими четвертькольцами.
Залить слабым 3~4%-ым яблочным уксусом и оставить на 1 минуту. Затем уксус тщательно слить.
Смешать картофель, морковь и лук. При желании можно добавить мелко порезанный укроп.
Сложить все овощи в п/э пакет.Влить туда масло и насыпать соль, перец и паприку. Также можно добавить другие специи по желанию.
Пакет надуть, горлрвину пакета закрутить.Потрясти пакет, чтобы специи распределились равномерно.
Высыпать овощи из пакета в посуду для запекания; разровнять.Сверху разложить порезанный бекон.Поставить в разогретую до t=200~220°C духовку.
Через 15~20 минут, когда сало из бекона вытопится, закрыть овощи сверху листом фольги.
Ещё через 20 минут попробовать проколоть картофель ножом. Если нож входит легко, то блюдо готово.
Если нож входит с усилием и с поскрипыванием, то оставить блюдо в духовке ещё на 10 минут и провести проверку снова.
Допекать до тех пор, пока картофель не станет мягким.');


CREATE TABLE INGREDIENT(
	id_recipe INTEGER,
	ingredient VARCHAR(50),
	quantity VARCHAR(50),
	dimension VARCHAR(20)
);

ALTER TABLE INGREDIENT ADD FOREIGN KEY (id_recipe)
REFERENCES RECIPE (id_recipe) ON DELETE CASCADE;

insert into INGREDIENT(id_recipe, ingredient, quantity, dimension)
values(1, 'куриные грудки', '1', 'kg');

insert into INGREDIENT(id_recipe, ingredient, quantity, dimension)
values(1, 'бекон в нарезке', '150', 'g');

insert into INGREDIENT(id_recipe, ingredient, quantity, dimension)
values(1, 'соль, перец, уксус, приправы', '~', '~');

insert into INGREDIENT(id_recipe, ingredient, quantity, dimension)
values(2, 'банка фасоли', '350', 'g');


insert into INGREDIENT(id_recipe, ingredient, quantity, dimension)
values(2, 'огурец', '150', 'g');

insert into INGREDIENT(id_recipe, ingredient, quantity, dimension)
values(2, 'побеги зеленого лука', '30', 'g');

insert into INGREDIENT(id_recipe, ingredient, quantity, dimension)
values(2, 'банка горбуши ', '350', 'g');

insert into INGREDIENT(id_recipe, ingredient, quantity, dimension)
values(2, '2 ст ложки растительного масла, 2 ч ложки 3~4% яблочного уксуса, 1 ч ложка горчицы, перец при желании', '~', '~');

insert into INGREDIENT(id_recipe, ingredient, quantity, dimension)
values(3, 'картофель', '1,5', 'kg');

insert into INGREDIENT(id_recipe, ingredient, quantity, dimension)
values(3, 'морковь', '300', 'g');

insert into INGREDIENT(id_recipe, ingredient, quantity, dimension)
values(3, 'лук', '100', 'g');

insert into INGREDIENT(id_recipe, ingredient, quantity, dimension)
values(3, 'бекон', '350', 'g');

insert into INGREDIENT(id_recipe, ingredient, quantity, dimension)
values(3, '3 ст ложки растительного масла, яблочный уксус, соль, перец, молотая паприка, укроп', '~', '~');



CREATE TABLE CATEGORY(
	id_recipe INTEGER,
	category VARCHAR(50)
);

ALTER TABLE CATEGORY ADD FOREIGN KEY (id_recipe)
REFERENCES RECIPE (id_recipe) ON DELETE CASCADE;

insert into CATEGORY(id_recipe, category)
values(1, 'Закуски');

insert into CATEGORY(id_recipe, category)
values(4, 'Десерты');

insert into CATEGORY(id_recipe, category)
values(2, 'Салаты');

insert into CATEGORY(id_recipe, category)
values(3, 'Горячие блюда');


CREATE TABLE ADVICE(
	id_recipe INTEGER,
	advice VARCHAR(100)
);

ALTER TABLE ADVICE ADD FOREIGN KEY (id_recipe)
REFERENCES RECIPE (id_recipe) ON DELETE CASCADE;

insert into ADVICE(id_recipe, advice)
values(2, 'fsdfsdfsdfsdffffffffffffffgtyrhrth');

insert into ADVICE(id_recipe, advice)
values(1, 'tertertertertertertreterterterterterte');

insert into ADVICE(id_recipe, advice)
values(2, 'l;lkjhggggggggggytuitiykjuhkhjkhjkhjkhj');