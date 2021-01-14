# methuselah
A Long-Lived History Manager

Named after the character in Redwall, the long-lived mouse that knew all the
history and was old when the oldest living mice were children.

I wanted to make an SCM for fun, and here we are.

# Overview

## Basics

All strings are unicode strings, including emails, tags, timestamps, etc.

Timestamps are stored in some binary format and are always displayed in ISO
format.

## Capture

A file can be captured. This means that its contents is stored under
its sha256sum in the folder `./.methuselah/objects/<first2chars>/<next2chars>`
and the sha256sum and timestamp of storage will be placed in a database. This
sum will be associated with the relative file name in an H2 database.

Capture does not happen automatically. All files are ignored until
capture is requested for a file or directory.

Captures can be deleted only if they are not currently part of a freeze.

Captures are associated with timestamp and email but not message.

## Freezing

A set of files can be frozen. This means that the current (filename, sha256sum)
tuples of each file can be captured together in a group for the whole
directory. This freeze is recorded together with a timestamp.

Automatic capture of all filenames currently associated with a freeze is
permitted.

A freeze records all the captures associated with it, together with an email of
the author, a timestamp, and an optional message.

Freezes do not have parents and any aspect of the freeze other than what
captures it records -- email, timestamp,  message -- can be edited at any time
by the user.

Freezes are be taken automatically by inspecting the crrent directory for any
previously captured files. Then the latest capture of these files are frozen
together. If all captures found exist exactly in a different freeze, the freeze
is aborted gracefully.

## Tagging

Freezes can be tagged. A freeze may have as many free form tags as may be.
Tags can be added to freezes or removed at any time.

## Viewing

Unfrozen captures can be listed via a command.

Freezes can be listed. The default listing does this by descending order
sorted by timestamp, then author. Freezes can be filtered by one or more tags.

## Syncing

All the freezes and captures of one repository can be synced into another over
HTTPS.

## Merging

Two freezes can be selected for merge, creating a new freeze. For each file
that exists in both, it must be decided which version of the file is used.
--ours, --theirs "yes to all" options can be specified. Intrafile merges not
supported in the initial version.

## Checkout

A file or whole directory can be checked out previous capture or freezing.
The file or set of files is unapologetically copied from the object store into
where they were before.
