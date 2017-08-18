# CV TIM Builder REST Service
This REST service is responsible for loading data required by the TIM Builder.

To load different data (RSUs, mileposts, ITIS codes, and ITIS code categories) there are CSV files provided that can be changed. These CSV files will be loaded into the MySQL Docker container using the initSql.sql script located at jpo-tim-builder/mysql/init/

Any changes to CSV files will have to stay true to the current CSV format for the database import to work. 

** NOTE: the MySQL container needs to be rebuilt for this init script to rerun. Not doing so will result in no CSV or init script changes occuring. 


