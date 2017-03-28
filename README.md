# MarketData

There is no better feeling than finding and working a real world problem and solving it.  It becomes challenging
when one is working in that field and at the same time trying to learn/understand/solve problems in that field.
In my case, I have been working in the financial industry for a long time. When I was not working on Algo Trading,
I created an Algo Trading project (available in GitHub) and started plugging away.  While working on that project,
I found a professional developer opportunity to work on an Algo Trading application.  I accepted the job and put my
github activity on hold.  I continued to work offline but couldn't publish anything on GitHub because that would be
conflict of interest.

At my day job, I saw another team doing some cool things by taking orders and routing them to different markets using
custom algorithms (smart order router - SOR).  Their system was written in C++ and I was truly facinated the way were
routing orders in microsecond latencies.  I created another repo on GitHub and found a SOR research paper online and
started working on an C++11 implementation.  There was no conflict of interest with my day job nor I had access to what other team
was doing. I worked on GH SOR for several months and then life hit me... I put the SOR project on-hold for some time.  When I wanted 
to get back to it, I found out that my day job will add SOR functionality so SOR repo needs to be on hold (again).  However, nothing was lost. I still learned enough on my own and now I am in a better position now then before to help my day job add SOR capabilities.

This project - MarketData - is an interesting high performance / low latency problem.  I work with market data in my
day job but I don't collect data from exchanges, instead there is another team that connects to various providers,
collects data, converts to company specific format and publishes to other applications (e.g. my application). The market
data collection application is written in C++, however they provide Java library for other application teams to consume
market data.  I don't have access to application code nor I want it because I want to try fresh ideas and learn by
making mistakes.

Therefore, in this project, I will build market data collection and distribution services.  The goals are:

- Java 1.8
- Low latency / High throughput / garbage free.
- Internal conflation: 0%
- Messages per second: 1,000,000

