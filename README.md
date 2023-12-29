Bước 1: Cài đặt IDE (Intellij,Net beans,Ecplipse)
cài đặt database (MySql,SQL server,warm)
Bước 2: Import file source code vào IDE, trong file source có file sql import vào database
Bước 3: thay đổi cấu hình trong app trong project 
resources/application.propertie
MySql:
spring.datasource.url=jdbc:mysql://localhost:3306/ten_database
spring.datasource.username=ten_nguoi_dung
spring.datasource.password=mat_khau
SQL:
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=ten_database
spring.datasource.username=ten_nguoi_dung
spring.datasource.password=mat_khau
Bước 4: run app
