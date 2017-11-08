date1 = date("2017-10-31 11:1:1","yyyy-MM-dd HH:mm:ss");
date2 = date("2017-11-2","yyyy-MM-dd");
time = date("11:1:2","HH:mm:ss");
println(dateAdd("YEAR",1,date1));
println(dateAdd("year",2,date1));
println(dateDiff("WEEK",date1,date2,"wednesday"));
println(dateName("WEEKDAY",date1));
println(datePart("WEEK",date1,"monday"));
println(dateTrunc("WEEKDAY",date1));
println(day(date1));
println(makeDate(2010,10,1));
println(makeDateTime(date2,time));
println(month(date1));
println(year(date1));
println(now());
println(today());
println(max(date1,date2));
println(date1.string());
println(date1.string("yyyy-MM-ss"));