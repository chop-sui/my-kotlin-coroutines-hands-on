package tasks

import contributors.User

/*
TODO: Write aggregation code.

 In the initial list each user is present several times, once for each
 repository he or she contributed to.
 Merge duplications: each user should be present only once in the resulting list
 with the total value of contributions for all the repositories.
 Users should be sorted in a descending order by their contributions.

 The corresponding test can be found in test/tasks/AggregationKtTest.kt.
 You can use 'Navigate | Test' menu action (note the shortcut) to navigate to the test.
*/
fun List<User>.aggregate(): List<User> {
    
    // FIXME: we can use `groupBy` method to get a map from login to all occurrences of the user with this login
    //  and then use `map` method to for each map entry to count the total number of contributions for each user
    //  by creating a new instance of `User` and finally sort the resulting list with `sortedByDescending`
    //  Much more functional/Kotliny and concise
    
    val usernameToContribMap: MutableMap<String, User> = mutableMapOf()
    
    this.forEach {
        if (!usernameToContribMap.containsKey(it.login))
            usernameToContribMap[it.login] = it
        else {
            val newContributions = usernameToContribMap[it.login]!!.contributions + it.contributions
            usernameToContribMap[it.login] = User(it.login, newContributions)
        }
    }
    
    return usernameToContribMap.values
        .toList()
        .sortedByDescending { it.contributions }
        
}