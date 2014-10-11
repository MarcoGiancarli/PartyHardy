Party Hardy
===========

Mobile app that makes partying better, safer, and easier.

---

Dependencies and libraries:

---

External:
- Parse 1.5.1
- Facebook SDK (Must have installed API level 8)

Android Libraries:
- (min SDK version:	8)
- (target SDK version:	21)
- Compiles with API 20
- android-support-v4.jar


To do:
---

Items in progress marked with asterisk and completed items with two asterisks.

<b>FRONT END</b>

Activity/Fragment architecture:

MainActivity *
activity_wide_main.xml <-- for tablet (and maybe horizontal phone) compatability

EventListTabFragment *
- EventListFragment *
- EventDetailFragment *

MyMapTabFragment *
- MapFragment *
- EventDetailFragment *

GroupsTabFragment *
- GroupsListFragment
- GroupFragment
- MapFragment <-- or perhaps a GroupMapFragment that wraps MapFragment?
- MakeGroupFragment

MeTabFragment *
- MyProfileFragment
- FriendsListFragment
- AddFriendFragment
- MyEventsFragment
- MakeEventFragment

SettingsActivity <-- access from action bar icon

<b>BACK END</b>