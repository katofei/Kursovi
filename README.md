# Kursovi

~~Вопрос - как хранить assign? как сущность либо просто полями в юзере~~
- сделано полями

#### TODO :
1. ~~необходимо добавить кварц/крон. считать дни до конца assign~~
        (было добавлено в ScheduledNotificationController)
          не протещено

2. ~~необходимо добавить кварц/крон. считать дни до дедлайна проекта~~
         (было добавлено в ScheduledNotificationController)
           не протещено

3. ~~подумать, нужно ли добавлять кварц/крон для task-ok~~
     (наверно всё таки - да!)
     (было добавлено в ScheduledNotificationController)
     не протещено

    3.1 придумать как посылать нотификации :  
    смотри AdminController (may be need to migrate to rest controllers)
    ~~заведение новой таски, дашборды (easy)~~
    нового асайна для пользователя  (не очень easy)

     ~~3.1.2 Реализовать для админа возмонжость асайнить пользователей на новый проект~~
                        не протестировано

4. Сделать создание дашбордов и тасок ПОД ними
               (сделаны сервис репозиторий и контролеер
               ничего не протестировано
               нет представлений)

5.                                      ** OPTION**
      _/profile/${currentUser.userId}/my-tasks - use "myTasks" page, + filter by user ( as creator and as executor)
                  this stuff started (MotherFUCKER) судя по всему не сделано
        /profile/${currentUser.userId}/user-statistics - use page with name "userStatistics"
      /profile/${currentUser.userId}/team-statistics - use page with name "teamStatistics"_

6. перепроверить и переделать маппинг по всему проекту

7. "Logout" action should be created

8. integrate ftp server and file loading/ downloading and views