# Advance Database Systems Project 1


## Project Description 
In this project, you will implement an information retrieval system that exploits user-provided relevance feedback to improve the search results returned by Bing. The relevance feedback mechanism is described in Singhal: Modern Information Retrieval: A Brief Overview, IEEE Data Engineering Bulletin, 2001, as well as in Chapter 9, “Relevance Feedback & Query Expansion,” of the Manning, Raghavan, and Schütze Introduction to Information Retrieval textbook, available online.

User queries are often ambiguous. For example, a user who issues a query [jaguar] might be after documents about the car or the animal, and—in fact—search engines like Bing and Google return pages on both topics among their top 10 results for the query. In this project, you will design and implement a query-reformulation system to disambiguate queries and—hopefully—improve the relevance of the query results that are produced. Here’s how your system, which should be written in Java or Python (your choice), should work:

Receive as input a user query, which is simply a list of words, and a value—between 0 and 1—for the target “precision@10” (i.e., for the precision that is desired for the top-10 results for the query, which is the fraction of pages that are relevant out of the top-10 results).
Retrieve the top-10 results for the query from Bing, using the Bing Search API (see below), using the default value for the various Bing parameters, without modifying these default values.
Present these results to the user, so that the user can mark all the web pages that are relevant to the intended meaning of the query among the top-10 results. For each page in the query result, you should display its title, URL, and description returned by Bing.

IMPORTANT NOTE: You should display the exact top-10 results returned by Bing for the query (i.e., you cannot add or delete pages in the results that Bing returns). Also, the Bing API has a number of search parameters. Please do not modify the default values for these search parameters.

If the precision@10 of the results from Step 2 for the relevance judgments of Step 3 is greater than or equal to the target value, then stop. If the precision@10 of the results is zero, then you should also stop. Otherwise, use the pages marked as relevant to automatically (i.e., with no further human input at this point) derive new words that are likely to identify more relevant pages. You may introduce at most 2 new words during each round.

IMPORTANT NOTE 1: You cannot delete any words from the query; you can just add words, up to 2 new words in each round. Also, your queries must consist of just keywords, without any additional operators (e.g., you cannot use negation, quotes, or any other operator in your queries).

IMPORTANT NOTE 2: The order of the words in the expanded query is important. Your program should automatically consider the alternate ways of ordering the words in a modified query, and pick the order that is estimated to be best.

Modify the current user query by adding to it the newly derived words in the best possible order, as determined in Step 4, and go to Step 2.