# daily_report
Формирование ежедневных отчетов. Сервис динамически в рандомном порядке формирует записи ежедневного отчета, для дальнейшей отправки в другой сервис, для подтверждения отчета. Если отчет заполнен  корректно, а в нашем случае - это нет строк со значением "Ничего не делал", то сервис принимающий отчет для проверки вернет результат типа "Булево" Истина. Имея положительный результат проверки, записи ежедневного отчета удалятся. В обратном случае, сервис будет возвращать результат Ложь и удаления происходит не будет, пока пользователь вручную не поправит запись в БД.

# Стек
1) Spring Boot
2) Spring WEB
3) Spring OpenFeign
4) Spring Data JDBC

# Основной класс
Основным классом является SchedulService, где выполняются регламентные операции, по формированию ежедневного очтета и отправки его на проверку в другой сервис.