

## Classic Tokenizer
In: "Please, email john.doe@foo.com by 03-09, re: m37-xq."
Out: "Please", "email", "john.doe@foo.com", "by", "03-09", "re", "m37-xq"

## KeywordTokenizerFactory
In: "Please, email john.doe@foo.com by 03-09, re: m37-xq."
Out: "Please, email john.doe@foo.com by 03-09, re: m37-xq."

## LetterTokenizerFactory
In: "I can't."
Out: "I", "can", "t"

## LowerCaseTokenizerFactory
In: "I just LOVE my iPhone!"
Out: "i", "just", "love", "my", "iphone"

## NGramTokenizerFactory

In: "hey man"
Out: "h", "e", "y", " ", "m", "a", "n", "he", "ey", "y ", " m", "ma", "an"



## Edge N-Gram Tokenizer
In: "babaloo"
Out: "b"

## f_WhitespaceTokenizerFactory
In: "To be, or what?"
Out: "To", "be,", "or", "what?"

----------------------
## field type semicolonDelimited
name_9

## DictionaryCompoundWordTokenFilterFactory
In: "Donaudampfschiff dummkopf"
Tokenizer to Filter: "Donaudampfschiff"(1), "dummkopf"(2),
Out: "Donaudampfschiff"(1), "Donau"(1), "dampf"(1), "schiff"(1), "dummkopf"(2), "dumm"(2), "kopf"(2)
Unicode Collation

ASCII Folding Filter
Beider-Morse Filter
Classic Filter
Common Grams Filter
Collation Key Filter
Edge N-Gram Filter
English Minimal Stem Filter
Hunspell Stem Filter
Hyphenated Words Filter
ICU Folding Filter
ICU Normalizer 2 Filter
ICU Transform Filter
Keep Words Filter
KStem Filter
Length Filter
Lower Case Filter
N-Gram Filter
Numeric Payload Token Filter
Pattern Replace Filter
Phonetic Filter
Porter Stem Filter
Position Filter Factory
Remove Duplicates Token Filter
Reversed Wildcard Filter
Shingle Filter
Snowball Porter Stemmer Filter
Standard Filter
Stop Filter
Synonym Filter
Token Offset Payload Filter
Trim Filter
Type As Payload Filter
Type Token Filter
Word Delimiter Filter
Related Topics

---------

HyphenationCompoundWordTokenFilter  ?
-----------------------------
# Phonetic Matching

Analysis Screen of the Solr Admin Web interface.
<fieldType name="mytextfield ... />
<fieldType name="mytextfield2 ... />

## DoubleMetaphoneFilterFactory
```xml
<fieldtype name="phonetic" stored="false" indexed="true" class="solr.
TextField" >
<analyzer>
<tokenizer class="solr.StandardTokenizerFactory"/>
<filter class="solr.DoubleMetaphoneFilterFactory" inject="false"/>
</analyzer>
</fieldtype>
```

```xml
<add>
<doc>
<field name="id">1</field>
<field name="name">Phone</field>
</doc>
<doc>
<field name="id">2</field>
<field name="name">Fone</field>
</doc>
</add>
```
<add>
<doc>
<field name="id">14</field>
<field name="name">appreciate</field>
</doc>
<doc>
<field name="id">15</field>
<field name="name">eppreciate</field>
</doc>
</add>
