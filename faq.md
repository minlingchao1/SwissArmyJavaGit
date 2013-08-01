---
layout: index
title: FAQ
---

### General Questions

  1. Why would I want to use JavaGit?
  2. Who are the intended users of JavaGit?
  3. Do I still need git instaled?
  4. Where can I get the source?
  5. Why not implement git using java (this way you don't need git installed)? 
  6. How is JavaGit different from Jgit/Egit?
  7. How can I contribute?

### Trouble Shooting

  1. I've just installed git for the first time on this computer and I am getting this wierd error: "Your name cannot be determined from your system services". How can I fix it? 

### General Questions

  1. Why would I want to use JavaGit?   
  
So that your java application can access, create and modify git repositories
with as little work as possible. Without JavaGit, you will need to write and
manage forking code yourself, flex your JNI muscle or reimplement git in Java
yourself.

  

  2. Who are the intended users of JavaGit?   
  
Java developers who want to access, create or modify git repositories from
their own applications.

  

  3. Do I still need git installed?   
  
Yes. JavaGit currently uses the command-line git client to interface with git
repositories. There are currently no plans for a pure java git implementation,
but we are open to doing such an implementation in the future. If this is
super important to you, let us know.

  

  4. Where can I get the source?  
  
The code is in a publicly accessible GIT repository :

    git clone https://github.com/bit-man/SwissArmyJavaGit.git
  

  5. Why not implement git using java (this way you don't need git installed)?   
  
We'd LOVE to, but unfortunately we don't have the time. We are gladly
accepting patches. ;-)

  

  6. How is JavaGit different from [JGit/EGit](http://repo.or.cz/w/egit.git)?   
  
JGit is a pure Java implementation of a git client, developed in tandem with
EGit, an [Eclipse](http://www.eclipse.org/) plugin for git. While JGit is
mature, full-featured, and flexible, it does have a few drawbacks:

  
1. Documentation for JGit is sparse. For a developer, using it for a project
would mean spending time combing through JGit source; ideally, we wanted an
API where a .jar, the JavaDocs, and an introductory HOWTO would be sufficient.

  
2. It's bound to a pure Java implementation of git functionality. We aimed for
an architecture where, if we should later want to hook into native git C
(using [JNI](http://en.wikipedia.org/wiki/Java_Native_Interface) or some other
Java/C bridge), or even provide pure Java implementations, we could do so with
no public-facing API impact.

  
3. JGit is tied on some level to EGit. We'd like to provide a library that's
designed to stand on its own from day one.

  
After some consideration, we decided to create our own git API, intended to
address the above concerns. We're not trying to take away from JGit - it's far
more feature-rich than JavaGit at present. But competition is a good thing,
and we hope to bridge that feature gap.

  

  7. How can I contribute?   
  
Join the conversation on the [developer mailing list](https://groups.google.com/forum/#!forum/swiss-army-java-git). 
If there is something you want to work on, let us know.  
If you want to work on the project but don't
know where to start, introduce yourself and the team can help you find
something to work on. There are lots of things to do.

  

### Trouble Shooting

  1. I've just installed git for the first time on this computer and I am getting this wierd error: "Your name cannot be determined from your system services". How can I fix it?   
  
This is happening because your global username and email address have not been
set up yet. To set your default username and email adderess, run the following
git commands (be sure you use your information in place of the example
information):

  
    git config --global user.email "you@example.com"
    git config --global user.name "Your Name"

  
If you prefer to set your identity only in the current repository, omit the '
--global' option from the above commands.

  
