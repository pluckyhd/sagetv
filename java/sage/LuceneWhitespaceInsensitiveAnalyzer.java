/*
 * Copyright 2015 The SageTV Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sage;

import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.ReusableAnalyzerBase;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.WhitespaceTokenizer;
import org.apache.lucene.util.Version;

import java.io.Reader;

/**
 * TODO: Insert description here. (generated by codefu)
 */
public class LuceneWhitespaceInsensitiveAnalyzer extends ReusableAnalyzerBase {

  private Version matchVersion;

  LuceneWhitespaceInsensitiveAnalyzer(Version version) {
    matchVersion = version;
  }

  @Override
  protected TokenStreamComponents createComponents(final String fieldName, final Reader reader) {
    final WhitespaceTokenizer src = new WhitespaceTokenizer(matchVersion, reader);

    // Whitespace spilt -> Lower Case filter -> NTE token generator
    // "SpongeBob" -> ["spongebob"] -> ["spongebob" "776649262"]
    TokenStream tok = src;
    tok = new LowerCaseFilter(matchVersion, tok);
    tok = new SageNTEFilter(tok);
    return new TokenStreamComponents(src, tok);
  }
}