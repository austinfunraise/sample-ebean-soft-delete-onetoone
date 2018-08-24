@OneToOne Soft Delete Tests
=====================

This shows an example where two entities are joined by a OneToOne relationship, and a soft delete is not respected.

If the "child" in the relationship is soft-deleted, then it is no longer returned by direct queries from the finder. If the parent is refreshed though, the parent can load the now-deleted child through the relationship on the parent   

