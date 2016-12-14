### SPOT 
(Serialization Protocol for Object Transfer)

This is meant to be replacement to JSON format, since JSON should be better in transferring data that is repeated several times. Spot should be a format which enables to reffer to same data and cause less traffic when exchanging that data. It also need to be simple and need to use less used signs as reserved symbols in order to make less trouble with escaping it.

### Language support

It is innitially developing in Java, but intention is to support much more languages. First next support would be to JavaScript.  

### Syntax

Unlike other formats, SPOT is not trying to resemble other's languages syntax, but to be slightly different in order 
to enable serialization and transfer of content which is already coded in other languages, without much of character 
escaping. That is why comma's, quotas, apostrophes and curly brackets are not reserved characters in the SPOT format. 
Instead of those, SPOT is using hash(#), bang(!), at(@), pipe(|), tilda(~)   

` ~ : used for escaping reserved characters by sorrounding escaped text between two of those` 

`| : is the separator between elements, between object name and first element and ends the last element`

`! : sorrunding arrays which can have zero, one, or more elements`

`@ : folowed by alias of previously defined object or reference`

`# : sorrounds referenced section. In case of definition of reference it must be followed by @ and than alias which 
will replace reference in furthure content`

`$# : starting block with a call to the referenced section. This block ends with #`

Serialization is relying of provided data sequence. Provided data needs to be serialized in the same order as the 
defined in the previous object definition.

### Math operations

It is possible to use special math operations inside $# ref # block of reference. Basic operations are `+`, `-` and 
`*`, but there are also `++`, `--`, `%`, `/` and `!`  

### Examples

<code>Adult |public|protected|none|gender|private|age|!children!|address|height|!siblings!|!parents!|naturallyConcieved|@A</code><br />
<code>A |true|null|false||M|true|35| !Mellanie|Peter! ||6.62| !! | !John|Miriam! |0|@first</code><br />
<code>A |$#first#|</code>
