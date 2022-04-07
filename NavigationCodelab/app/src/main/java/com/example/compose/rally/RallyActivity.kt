/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.compose.rally

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.compose.rally.data.UserData
import com.example.compose.rally.ui.accounts.AccountsBody
import com.example.compose.rally.ui.accounts.SingleAccountBody
import com.example.compose.rally.ui.bills.BillsBody
import com.example.compose.rally.ui.components.RallyTabRow
import com.example.compose.rally.ui.overview.OverviewBody
import com.example.compose.rally.ui.theme.RallyTheme

/**
 * This Activity recreates part of the Rally Material Study from
 * https://material.io/design/material-studies/rally.html
 */
class RallyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RallyApp()
        }
    }
}

@Preview
@Composable
fun RallyAppPreview() {
    RallyApp()
}

/**
 * The NavController
 *
 * The NavController is the central component when using Navigation in Compose;
 * it keeps track of back stack entries, moves the stack forward, enables back stack manipulation, and navigating between screen states.
 * Because NavController is central to navigation it has to be created first in order to navigate to destinations.
 *
 *
 *
 * NavHostController
 *
 * Within Compose you're working with a NavHostController, which is a subclass of NavController.
 * Obtain a NavController by using the rememberNavController() function;
 * this creates and remembers a NavController which survives configuration changes (using rememberSavable).
 * The NavController is associated with a single NavHost composable.
 * The NavHost links the NavController with a navigation graph where composable destinations are specified.
 * For this codelab, obtain and store your NavController within RallyApp. It is the root composable for the entire application. You can find it in RallyActivity.kt.
 * */
@Composable
fun RallyApp() {
    RallyTheme {
        val allScreens = RallyScreen.values().toList()
//        var currentScreen by rememberSaveable { mutableStateOf(RallyScreen.Overview) }
        val navController: NavHostController = rememberNavController()

        /**
         * currentBackStackEntryAsState()
         *
         * Gets the current navigation back stack entry as a MutableState.
         * When the given navController changes the back stack due to a NavController.
         * navigate or NavController.popBackStack this will trigger a recompose and return the top entry on the back stack.
         * */
        val backstackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = RallyScreen.fromRoute(
            backstackEntry.value?.destination?.route
        )

        Scaffold(
            topBar = {
                RallyTabRow(
                    allScreens = allScreens,
//                    onTabSelected = { screen -> currentScreen = screen },
                    /**
                     * To re-enable this behavior, the currentScreen property needs to be updated as well.
                     * Luckily Navigation holds on to the back stack for you and can provide you with the current back stack entry as a State.
                     * With this State you can react to changes to the back stack.
                     * You can even query the current back stack entry for its route.
                     * */
                    onTabSelected = { screen -> navController.navigate(screen.name) },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            RallyNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
/*
            *//**
             * NavHost
             *
             * The NavHost links the NavController with a navigation graph where composable destinations are specified.
             * Pass in the navController we created in the previous step.
             * The NavHost also needs a startDestination
             * *//*
            NavHost(
                navController = navController,
                startDestination = RallyScreen.Overview.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                *//**
                 * NavGraphBuilder
                 *
                 * Now we can define our nav graph.
                 * The destinations that the NavHost can navigate to are ready to accept destinations.
                 * We do this using a NavGraphBuilder, which is provided to the last parameter of NavHost; a lambda for defining your graph.
                 * As this parameter expects a function you can declare destinations in a trailing lambda.
                 * The Navigation Compose artifact provides the NavGraphBuilder.composable extension function.
                 * Use it to define navigation destinations in your graph.
                 * *//*
                composable(RallyScreen.Overview.name) {
                    OverviewBody(
                        onClickSeeAllAccounts = { navController.navigate(RallyScreen.Accounts.name) },
                        onClickSeeAllBills = { navController.navigate(RallyScreen.Bills.name) },
                        onAccountClick = { name ->
                            navigateToSingleAccount(navController, name)
                        }
                    )
                }
                composable(RallyScreen.Accounts.name) {
                    AccountsBody(accounts = UserData.accounts) { name ->
                        navigateToSingleAccount(
                            navController = navController,
                            accountName = name
                        )
                    }
                }
                composable(RallyScreen.Bills.name) {
                    BillsBody(bills = UserData.bills)
                }
            }*/


            /*Box(Modifier.padding(innerPadding)) {
                currentScreen.content(
                    onScreenChange = { screen ->
                        currentScreen = RallyScreen.valueOf(screen)
                    }
                )
            }*/
        }
    }
}

val accountsName = RallyScreen.Accounts.name

@Composable
fun RallyNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = RallyScreen.Overview.name,
        modifier = modifier
    ) {
        composable(RallyScreen.Overview.name) {
            OverviewBody(
                onClickSeeAllAccounts = { navController.navigate(RallyScreen.Accounts.name) },
                onClickSeeAllBills = { navController.navigate(RallyScreen.Bills.name) },
                onAccountClick = { name ->
                    navigateToSingleAccount(navController, name)
                }
            )
        }
        composable(RallyScreen.Accounts.name) {
            AccountsBody(accounts = UserData.accounts) { name ->
                navigateToSingleAccount(
                    navController = navController,
                    accountName = name
                )
            }
        }
        composable(RallyScreen.Bills.name) {
            BillsBody(bills = UserData.bills)
        }

        composable(
            "$accountsName/{name}", // 1. route와 parameter이름 정의 // route: "Accounts/{name}, argument name: "name"
            arguments = listOf(
                navArgument("name") {
                    // Make argument type safe
                    type = NavType.StringType // 2. parameter의 type 정의
                }
            )
        ) { entry -> // Look up "name" in NavBackStackEntry's arguments
            val accountName = entry.arguments?.getString("name")
            // Find first name match in UserData
            val account = UserData.getAccount(accountName)
            // Pass account to SingleAccountBody
            SingleAccountBody(account = account)
        }
        composable(
            "$accountsName/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                },
            ),
            deepLinks =  listOf(navDeepLink {
                uriPattern = "rally://$accountsName/{name}"
            }
            )
        ) {
            
        }

    }
}

private fun navigateToSingleAccount(
    navController: NavHostController,
    accountName: String
) {
    navController.navigate("${RallyScreen.Accounts.name}/$accountName")
}
