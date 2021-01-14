# methuselah
A Long-Lived History Manager

Named after the character in Redwall, the long-lived mouse that knew all the
history and was old when the oldest living mice were children.

I wanted to make an SCM for fun, and here we are.

# Overview


## Capture

A file can be captured. This means that its contents is stored under
its sha256sum in the folder `./.methuselah/objects` and the sha256sum
and timestamp of storage will be placed in a database. This sum will
be associated with the relative file name in an H2 database.

Capture does not happen automatically. All files are ignored until
capture is requested.


## Freezing

A set of files can be frozen. This means that the current (filename, sha256sum)
tuples of each file can be captured together in a group for the whole
directory. This freeze is recorded together with a timestamp.



Automatic capture of all filenames currently associated with a freeze is
permitted.

## Rollback

A file or whole directory can be rolled back to a previous capture or freezing.
The file or set of files is unapologetically copied from the object store into
where they were before.
k






