Примеры использования API для файла 10m.txt

Запрос:
URL: localhost:8080/operations/all
Тело:
{
    "operation": MAX
    "filePath": "/Users/ozodbek/Downloads/RelexProject/10m.txt"
}
Ответ:
{
    "operation": MAX,
    "answer": "49999978"
}

Запрос:
URL: localhost:8080/operations/all
Тело:
{
    "operation": MIN,
    "filePath": "/Users/ozodbek/Downloads/RelexProject/10m.txt"
}
Ответ:
{
    "operation": "MIN",
    "answer": "-49999996"
}

Запрос:
URL: localhost:8080/operations/all
Тело:
{
    "operation": MED,
    "filePath": "/Users/ozodbek/Downloads/RelexProject/10m.txt"
}
Ответ:
{
    "operation": "MED",
    "answer": "25216.0"
}

Запрос:
URL: localhost:8080/operations/all
Тело:
{
    "operation": AVG,
    "filePath": "/Users/ozodbek/Downloads/RelexProject/10m.txt"
}
Ответ:
{
    "operation": "AVG",
    "answer": "7364.418442641844"
}

Запрос:
URL: localhost:8080/operations/all
Тело:
{
    "operation": SEQ_INC,
    "filePath": "/Users/ozodbek/Downloads/RelexProject/10m.txt"
}
Ответ:
{
    "operation": "SEQ_INC",
    "answer": "[[-48190694, -47725447, -43038241, -20190291, -17190728, -6172572, 8475960, 25205909, 48332507, 48676185]]"
}

Запрос:
URL: localhost:8080/operations/all
Тело:
{
    "operation": SEC_DEC,
    "filePath": "/Users/ozodbek/Downloads/RelexProject/10m.txt"
}
Ответ:
{
    "operation": "SEQ_DEC",
    "answer": "[[47689379, 42381213, 30043880, 12102356, -4774057, -5157723, -11217378, -23005285, -23016933, -39209115, -49148762]]"
}

Запрос:
URL: localhost:8080/operations/get_max_value
Тело:
{
    "filePath": "/Users/ozodbek/Downloads/RelexProject/10m.txt"
}
Ответ:
{
    "operation": MAX,
    "answer": "49999978"
}

Запрос:
URL: localhost:8080/operations/get_min_value
Тело:
{
    "filePath": "/Users/ozodbek/Downloads/RelexProject/10m.txt"
}
Ответ:
{
    "operation": "MIN",
    "answer": "-49999996"
}

Запрос:
URL: localhost:8080/operations/get_median_value
Тело:
{
    "filePath": "/Users/ozodbek/Downloads/RelexProject/10m.txt"
}
Ответ:
{
    "operation": "MED",
    "answer": "25216.0"
}

Запрос:
URL: localhost:8080/operations/get_avg_value
Тело:
{
    "filePath": "/Users/ozodbek/Downloads/RelexProject/10m.txt"
}
Ответ:
{
    "operation": "AVG",
    "answer": "7364.418442641844"
}

Запрос:
URL: localhost:8080/operations/get_increase_sequence
Тело:
{
    "filePath": "/Users/ozodbek/Downloads/RelexProject/10m.txt"
}
Ответ:
{
    "operation": "SEQ_INC",
    "answer": "[[-48190694, -47725447, -43038241, -20190291, -17190728, -6172572, 8475960, 25205909, 48332507, 48676185]]"
}

Запрос:
URL: localhost:8080/operations/get_decrease_sequence
Тело:
{
    "filePath": "/Users/ozodbek/Downloads/RelexProject/10m.txt"
}
Ответ:
{
    "operation": "SEQ_DEC",
    "answer": "[[47689379, 42381213, 30043880, 12102356, -4774057, -5157723, -11217378, -23005285, -23016933, -39209115, -49148762]]"
}