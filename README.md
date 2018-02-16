# Kursovi
//TODO :
 Вопрос - как хранить assign? как сущность либо просто полями в юзере

    1.
        необходимо добавить кварц/крон. считать дни до конца assign
        (было добавлено в ScheduledNotificationController)
        не протещено

    2.
        необходимо добавить кварц/крон. считать дни до дедлайна проекта
         (было добавлено в ScheduledNotificationController)
                не протещено

    3.
        подумать, нужно ли добавлять кварц/крон для task-ok
          (наверно всё таки - да!)
           (было добавлено в ScheduledNotificationController)
                  не протещено

            1.1 придумать как посылать нотификации на :
            заведение новой таски, дашборды
            нового асайна для пользователя

    4.
        Сделать создание дашбордов и тасок ПОД ними
               (сделаны сервис репозиторий и контролеер
               ничего не протестировано
               нет представлений)
    5.        
      /profile/${currentUser.userId}/my-tasks - use "myTasks" page, + filter by user ( as creator and as executor)
                   this stuff started (MotherFUCKER) судя по всему не сделано

      /profile/${currentUser.userId}/user-statistics - use page with name "userStatistics"
      /profile/${currentUser.userId}/team-statistics - use page with name "teamStatistics"

    6.
        перепроверить и переделать маппинг по всему проекту
