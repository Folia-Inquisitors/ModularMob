# ModulerMob

# Official Discord 

https://discord.gg/aT9z7q7hX8

## Building instructions

mvn clean install
 
## Description

This plugin allows players to customize almost every aspect of mobs, and set rates for how often they spawn in. This is meant to be a simple plugin for servers. It is highly optimized and heavily tested. Feel free to constribute.

### Config for the spawner folder

```
# Choose what mobs to spawn in by replacing zombie with your desired mob
entity: zombie

chance:
- 70 natural
- # Natural is the rate that normal mobs spawn in by
- 50 leather-skeleton.yml
- # leather-skeleton.yml This is for the yml names of the mob you select.
- 5 low-tier-leggings-only-skeleton.yml
- - # low-tier-leggings-only-skeleton.yml You can add as many ymls as you want. The ymls here should correspond the a name of the yml in mobs folder.
```

## Config for the mobs folder

```
Here is an simple example of how a config could be used
https://pastebin.com/SHUxE5yn

Here is a complex example
https://pastebin.com/FeRq0BpY
```
## Admin Commands

```
/spawnmob ( Then put the YML name, if it didn't appear that mean it didn't load in. )

/modularmob:reloadmob

/modularmob:rlmob

```

### Folia inquisitors

[<img src="https://github.com/Folia-Inquisitors.png" width=80 alt="Folia-Inquisitors">](https://github.com/orgs/Folia-Inquisitors/repositories)
[<img src="https://github.com/HSGamer.png" width=80 alt="HSGamer">](https://github.com/HSGamer)
