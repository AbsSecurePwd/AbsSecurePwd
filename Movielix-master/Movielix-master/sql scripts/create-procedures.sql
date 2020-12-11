drop procedure if exists addstar;
drop procedure if exists addmovie;

delimiter //
create procedure addstar(in n varchar(100), in y int)
begin
	set @b := (select count(id) from stars where name=n and birthYear=y);
	if @b = 0 then
	
	
		/*get id*/
		set @aid := (select max(id) from stars);
		set @aid := (select substring_index(@aid, "m", -1));
		set @aid := convert(@aid, signed integer);
		set @aid := @aid + 1;
		set @aid := convert(@aid, char);
		set @aid := concat("nm", @aid);
		
		insert into stars(id, name, birthYear) values (@aid,n,y);
		
--		select "star doesnt exists";
	else
		select "star exist";
	end if;
end //
delimiter ;


delimiter //
create procedure addmovie(in t varchar(100), in y int, in d varchar(100), in s varchar(100), in g varchar(32))
begin
	set @b := (select count(id) from movies where title=t and year=y and director=d);
	if @b < 1 then 
	
	
		/*get id*/
		set @mid := (select max(id) from movies);
		set @mid := (select substring_index(@mid, "t", -1));
		set @mid := convert(@mid, signed integer);
		set @mid := @mid + 1;
		set @mid := convert(@mid, char);
		set @mid := concat("tt", @mid);
		
		insert into movies(id, title , year, director) values (@mid,t,y,d);
		
		/*check if star star exists*/
		set @s := (select count(id) from stars where name=s);
		if @s < 1 then /*doesnt exist*/
			set @aid := (select max(id) from stars);
			set @aid := (select substring_index(@aid, "m", -1));
			set @aid := convert(@aid, signed integer);
			set @aid := @aid + 1;
			set @aid := convert(@aid, char);
			set @aid := concat("nm", @aid);
			insert into stars(id, name) values (@aid,s);
			insert into stars_in_movies(starId, movieId) values(@aid, @mid);
		
		else /*star exists*/
			set @aid := (select id from stars where name=s limit 1);
			/*insert into stars(id, name) values (@aid,s);*/
			insert into stars_in_movies(starId, movieId) values(@aid, @mid);
		end if;
		
		
		/*check if given genre exists*/
		set @g := (select count(id) from genres where name=g);
		if @g > 0 then
			set @gid := (select id from genres where name=g);
			insert into genres_in_movies(genreId, movieId) values(@gid,@mid);
		else
			insert into genres(name) values(g);
			set @gid := (select id from genres where name=g);
			insert into genres_in_movies(genreId, movieId) values(@gid,@mid);
		end if;
		select "movie didnt exist";
		
	else
		select "movie exist";
	end if;
end //
delimiter ;










