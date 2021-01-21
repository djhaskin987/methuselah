# methuselah

A Long-Lived History Manager

Named after the character in Redwall, the long-lived mouse that knew all the
history and was old when the oldest living mice were children.

I wanted to make an SCM for fun, and here we are.

# Overview

## Basics

All strings are unicode strings, including emails, tags, timestamps, etc.

All timestamps will be recorded with the UTC timezone and converted to local
time upon display and from local time upon scan or input.

Timestamps are stored in some binary format and are always displayed in ISO
format.

## Image

A file's image can be captured. This means that its contents is stored under
its sha256sum in the folder `./.methuselah/objects/<first2chars>/<next2chars>`
and the sha256sum and timestamp of storage will be placed in a database. This
sum will be associated with the relative file name in an H2 database.

Image capture does not happen automatically. All files are ignored until
capture is requested for a file or directory.

Image capture can occurr using ant glob expressions.

Images can be deleted only if they are not currently part of a revision.

Images are associated with a timestamp and email but not a message.

There is no notion of a "working set", only of a database of changes.

Stashing is not a thing per se, though the functionality might be there through
command sugar.

## Revisions

A set of images can be compiled into an revision. This means that the current
(filename, sha256sum) tuples of currently unrecorded images can be
captured together in a group for the whole directory. This revision is recorded
together with a timestamp and a message.

Automatic image capture of all filenames currently associated with a revision is
permitted.

An revision records all the images that are currently reflected in the
directory, together with an email of the author, a timestamp, and an optional
message.

Revisions do not have parents and any aspect of the revision other than what
images it records -- email, timestamp,  message -- can be edited at any time
by the user.

Revisions are be taken automatically by inspecting the crrent directory for any
previously captured files. Then the latest image of these files are frozen
together. If all images found exist exactly in a different revision, the revision
is aborted gracefully.

In order to facilitate fast freezing, a sha256sum of all the sha256sums of
captures and their filenames is taken and recorded as part of the freeze.
This is then used to detect if a freeze should happen.

## Tagging

Revisions can be tagged. A revision may have as many free form tags as may be.
Tags can be added to revisions or removed at any time.

## Stories

Revisions are associated with one (and only one) *story*. Each revision has a
story order number or simply a story number.

Story names, descriptions and creation timestamps for that story are stored.

The latest story number is added to some default value for increase of number
(say 100) and this new number is assigned to any new revision. Story numbers,
as with anything else about the revision, may be edited at any time.

Revisions can be merged from different stories, but the resulting revision must
only be part of one story.

When an revision is moved betwen stories, this may happen, but the story ID and
story number must be changed at the same time for this to make any sense. User
specified.

Stories can be marked as "finished" or "unfinished". Finish timestamps are
recorded.

## Merge Points

Stories can have relationships with each other. A merge point can be placed
between any two stories. A story number of the origin story and a story number
for a destination story is allocated and recorded, and the latest story numbers
updated in the story records. Through command sugar, merge points can be
created from one story to another when it is created, and when revisions are
merged together; however, revisions do not have relationships to each other;
only their stories.

## Viewing

Unfrozen images can be listed via a command.

Revisions can be listed. The default listing does this by descending order
sorted by timestamp, then author. Revisions can be filtered by one or more tags.

## Syncing

All the revisions and images of one repository can be synced into another over
HTTPS.

Two stories can be synced. When this happens, the revisions in matching stories
that exist in the original repository that do not exist in the current
repository are "rebased" onto the revisions in the ingested repository.
This means the first novel revision from the origin repo is merged into the
latest revision from the ingested repostory to create a new latest revision in
the ingested repository. This revision is merged with the neext unknown revision
from the original repository, and so on, until all revisions within various
stories are ingested. Then these are put into the original repository.

New revisions with story numbers in the ingested repository -- there are two
cases. Either their story number is earlier than the repos' earliest commoon
story or it is later. If it is earlier, it is taken as is. If later, original
repo stories are similarly rebased onto ingested repository revisions and
stories before revisions are, and stories are put in place afterwards into the
new stories.

## Merging

Two revisions can be selected for merge, creating a new revision. For each file
that exists in both, it must be decided which version of the file is used.
--ours, --theirs "yes to all" options can be specified. Intrafile merges not
supported in the initial version. The new revision must be explicitly given a
story to which to belong.

## Checkout

A file or whole directory can be checked out previous image or freezing.
The file or set of files is unapologetically copied from the object store into
where they were before.

## Server Mode

There is a server mode. It can serve itself over HTTPS. It also has a built-in
API that lets you do things like:

* Sync
* Check out a particular file from a particular revision or tag
* Other stuff as needed

## Standalone, Syncing

Thre is a standalone mode, and a client mode.

Syncing mode means methuselah "knows" there is a main server that is being
used. most calls are calls to the server. Objects are only downloaded as needed.

