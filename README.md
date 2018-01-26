# AccessLogParser
Access Log Parser application.


The SQL to find IPs that made more than 100 requests starting from 2017-01-01.13:00:00 to 2017-01-01.14:00:00.
select * from ips_blocked where start_date >= '2017-01-01.13:00:00' and end_date <= '2017-01-01.14:00:00'

- Returns 2 rows.

The SQL query to find requests made by a given IP.
SELECT * FROM ip_blocked_events WHERE ip_address = '192.168.228.188'

- Returns 209 rows
