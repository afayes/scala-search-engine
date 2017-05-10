# Hasta la vista

Hasta la vista is a new search engine for web pages.

This is the first assignment for the Scala course. The full assignemnt documentation can be found at [Google drive](https://docs.google.com/document/d/1EvImwthb_nI72lyzUM4hkVhRoTUQU2xDNF08fgIB52A/edit#heading=h.90lp7upnfdhd)

Sprin boot and Scala [article](http://www.java-allandsundry.com/2014/03/spring-boot-and-scala.html)

## Note

This project structure is indicative only. You have to take your decision about software architecture and document it in the design document.

You are free to take any choice if it is proper documented. 

The only must is about using Scala and avoiding databases and framework not agreed in the class.

The module hlv-crawler is there as reference. Please, it is not a suggestion on how you have to create your application. Take it instead as template for any module structure you think is relevant.

Docker compose has been added, in case you need a quick reference. As stated above, take it as a possible template, suggestion. You are free to decide what to use, and how.



## Assignment text

### Hasta la vista

Acme Corporation is leader in providing supplies (for example the famous earthquake pills) and tools for any possible application (for example tornado kit). It has decided to step into the new digital era.  
Acme CEO Mr. Wile E. Coyote following this new business vision, has sponsored a request for proposal (RFP) to develop a new search engine.   
Mr. Coyote is a strong believer in practical approach because Acme market leadership has been obtained with practical inventions. It is not a surprise that the major success factor of this RFP is to provide a working prototype for the search engine.  
Acme marketing department has already named the future search engine “Hasta la vista”.

RFP requires that the proposal has to be presented in the short time possible with a design document and a working proof of concept (POC).

POC will be judged following the criteria below
* Speed of search. The velocity of retrieving the results it is the key
The system can be limited to only 100 web pages. They can be downloaded from internet or uploaded in a folder. It is recognized that the web is a graph, then “Hasta la vista” has to be able to deal with links (relative and absolute) in order to retrieve all the related web pages. 
* Acme believe that the secrecy is the key of success. Acme never had competitors for their most selling product because the secrecy of their patents. “Hasta la vista” project is not allowed to use third party or off-the-shelf applications (for example you cannot use a database). It is instead allowed to create everything you need to succeed from scratch.
* The search interface can be limited to a set of REST API, because Acme marketing believes that they know how to present a successful website
* Acme finance department instead requires some analytics features. Their major interest is to understand the system usage, like for example the most/least frequent search, most/least clicked web pages, most/least used search word. This data will be used in to fill in their Acme analytics system. They accepted that in the POC the integration can be done via REST API that you define
* “Hasta la vista” has to sort the results. The default orderings is related to relevancy, additional ordering are by retrieving and creation date. The relevance is calculated by the number of time that the search string appears in the document and how much distance there is between the words. 
For example:
Searching for “black cat”
Will show first all the pages where the string  “black cat” is more frequent, then all the document where the words “black” and “cat” appear, ordered by distance. The distance is calculated as for example
“This is a black and white cat” => distance 3
“This is a black purple cat” => distance 2
Any other more complex algorithm will be take in consideration as differentiator with the competitor


You have just finished reading all the Acme documentation and you’ve finished your Scala course. Now you are thinking, this is the opportunity of a lifetime. I can become a millionaire like Larry Page!!!

You get overexcited, but you quickly realise that you didn’t have time to learn all the fancy Scala framework and you have to deliver a working POC quickly…. The road to success is not so easy!

After a couple of espresso, and an amazing slice of your mother’s cake you get the idea!
It’s a POC, you can start with a technology and then change it when you win the deal!
So you decide to start using Maven and Spring Boot as base for your POC services.

Now, that you started to appreciate Scala, you look into Java and seems so gruelling, back-breaking, verbose and annoying to use. Then you decide to write everything in Scala and reduce to the minimum the interaction Spring - Scala.   
At the of the day, you think who cares about Spring dependency injection if I have the cake pattern?  
And you take and eat another slice of your mother’s cake.

So far, so good…   
You have the basic technology, you know that Scala Future can help with performance and Scala collection with higher-order function looks really promising what else do you need?
Suddenly, maybe the 3rd espresso was too much, you realise that you have in somehow to manage 3 big logical components.  
* Web crawler that load the 100 web pages and somehow prepare the data for you data store
* A datastore where to index the web pages content, metadata and analytics
* A user interface that offers the search and analytics API

You take your head between your hands, the you try to think… (it’s 2am now… the coffee effect stopped 2 hours ago…)  
After some attempts you see foggy, you do the extra step and with the last energy you get that the database seems exciting to develop, but in order to go quickly you need also a Scala Client API.   
You jump on your chair and you scream: “Amazing, I can also write some really smart and fucking advanced DB API!”  
Then silence, hoping that no one knock on the door  
With this though you go to sleep…  

The day after you start with the design…  
