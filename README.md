# java-utilities

A self-publishing Java library of utilities


## KeyUtil

Convert a short, int, or long into a reversible string representation
that's hard to predict but very quickly converted back to a number;
the primary use case is to create confirmation keys that seem random
but are in fact predictable.

## Deployment Notes

Deploying with a fix: or feat: tag should trigger an automated 
package release and upload.  Adding the phrase BREAKING CHANGE
in a commit body should trigger a major version bump, otherwise
a feat: gets minor and a fix: fix.  Use chore: or similar for no
release required.