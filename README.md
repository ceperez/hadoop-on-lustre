# Diskless Hadoop on Lustre

This repository contains the code changes that allows Hadoop to be run
on "diskless" Hadoop nodes that use Lustre for all storage (temporary
and permanent). This version of Hadoop should be built in a manner
identical to unpatched Hadoop.

Please see [this PDF](http://www.xyratex.com/sites/default/files/Xyratex_white_paper_MapReduce_1-4.pdf)
for a more detailed explanation of how this patch works.
The PDF is also included in the repository.

Here is a list of the bare minimum paramters that need to be changed in
order to run this software in "diskless" mode.

* **Filename** | **Configuration Parameter** | **Suggested value**
* core-site.xml | fs.default.name | file:///
* core-site.xml | hadoop.tmp.dir | Any location on the Lustre filesystem
* mapred-site.xml | mapreduce.jobtracker.staging.root.dir | {lustre\_mount\_point}/user
* mapred-site.xml | mapred.system.dir | {lustre\_mount\_point}/system
* mapred-site.xml | mapred.local.dir | Any directory on Lustre named "mapred_${host.name}"
* mapred-site.xml | mapreduce.shuffle.link | Set to “true”
* mapred-site.xml | hadoop.ln.cmd | Set to the full path for the system “ln” command
* mapred-site.xml | mapred.diskless.client.mode | Set to “true”
* hadoop-env.sh | HADOOP_OPTS Add | "-Dhost.name=\`hostname -s\`"
* hadoop-env.sh | "-XX:ErrorFile" and "-Xloggc" | Modify HADOOP\_NAMENODE\_OPTS,
  HADOOP\_JOBTRACKER\_OPTS, and HADOOP\_SECONDARYNAMENODE\_OPTS to point to
  non-volatile storage
* hadoop-env.sh | HADOOP\_LOG\_DIR and HADOOP\_SECURE\_DN\_LOG\_DIR | Modify the
  paths to point to non-volatile storage

For questions and other inquries, please contact us at
<hadoop.on.lustre@seagate.com>

Seagate Technology
November, 2014