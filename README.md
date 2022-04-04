# java-utilities

A self-publishing Java library of utilities

## KeyUtil

Convert a short, int, or long into a reversible string representation
that's hard to predict but very quickly converted back to a number;
the primary use case is to create confirmation keys that seem random
but are in fact predictable.